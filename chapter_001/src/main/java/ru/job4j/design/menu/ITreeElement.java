package ru.job4j.design.menu;

/**
 * Интерфейс для элементов дерева
 */
public interface ITreeElement {
    public ITreeElement getParent();
    public void setParent(ITreeElement parent);
    public int getDepth();
}
