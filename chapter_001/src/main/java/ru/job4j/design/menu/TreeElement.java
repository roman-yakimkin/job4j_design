package ru.job4j.design.menu;

/**
 * Интерфейс для элементов дерева
 */
public interface TreeElement {
    public TreeElement getParent();
    public void setParent(TreeElement parent);
    public int getDepth();
}
