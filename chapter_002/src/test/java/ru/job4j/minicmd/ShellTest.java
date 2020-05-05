package ru.job4j.minicmd;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ShellTest {
    @Test
    public void initShell() {
        Shell shell = new Shell();
        assertThat(shell.path(), is("/"));
    }

    @Test
    public void rootDirectory() {
        Shell shell = new Shell();
        shell.cd("/");
        assertThat(shell.path(), is("/"));
    }

    @Test
    public void thisDirectory() {
        Shell shell = new Shell();
        shell.cd("usr/.");
        assertThat(shell.path(), is("/usr"));
    }

    @Test
    public void parentDirectoryManyCommandLines() {
        Shell shell = new Shell();
        shell.cd("home").cd("roman").cd("..");
        assertThat(shell.path(), is("/home"));
    }

    @Test
    public void parentDirectoryOneCommandLine() {
        Shell shell = new Shell();
        shell.cd("/home/roman/projects/../download");
        assertThat(shell.path(), is("/home/roman/download"));
    }
}
