package com.hyd.filestorage.git;

import com.hyd.filestorage.Content;
import com.hyd.filestorage.Storage;
import com.hyd.filestorage.StorageException;
import java.io.IOException;
import java.net.URI;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.dfs.DfsRepositoryDescription;
import org.eclipse.jgit.internal.storage.dfs.InMemoryRepository;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;

public class GitStorage implements Storage {

    public static final String DEFAULT_BRANCH = "master";

    private URI repositoryUri;

    public GitStorage(URI repositoryUri) {
        this.repositoryUri = repositoryUri;
    }

    @Override
    public URI getUri() {
        return repositoryUri;
    }

    @Override
    public Content getContent(String path) {
        return getContent(DEFAULT_BRANCH, path);
    }

    public Content getContent(String branch, String path) {
        try {
            InMemoryRepository repository = createInMemoryRepository(repositoryUri);
            TreeWalk treeWalk = createTreeWalker(repository, branch, path);

            if (!treeWalk.next()) {
                return null;
            } else {
                return createGitContent(repository, treeWalk);
            }
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }

    private Content createGitContent(InMemoryRepository repository, TreeWalk treeWalk) throws IOException {
        ObjectId objectId = treeWalk.getObjectId(0);
        ObjectLoader loader = repository.open(objectId);
        return new GitContent(repositoryUri, loader);
    }

    private TreeWalk createTreeWalker(InMemoryRepository repository, String branch, String path) throws IOException {
        RevWalk revWalk = new RevWalk(repository);
        RevCommit revCommit = revWalk.parseCommit(repository.resolve("refs/heads/"+ branch));

        TreeWalk treeWalk = new TreeWalk(repository);
        treeWalk.addTree(revCommit.getTree());
        treeWalk.setRecursive(true);
        treeWalk.setFilter(PathFilter.create(path));
        return treeWalk;
    }

    private InMemoryRepository createInMemoryRepository(URI uri) throws GitAPIException {
        InMemoryRepository repository = new InMemoryRepository(new DfsRepositoryDescription());
        fetchRepository(repository, uri);
        return repository;
    }

    private void fetchRepository(InMemoryRepository repository, URI uri) throws GitAPIException {

        Git git = new Git(repository);

        git.fetch()
            .setRemote(uri.toString())
            .setRefSpecs(new RefSpec("+refs/heads/*:refs/heads/*"))
            .call();
    }
}
