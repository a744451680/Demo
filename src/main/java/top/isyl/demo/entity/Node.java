package top.isyl.demo.entity;

import lombok.Data;

import java.util.List;

/**
 * 搭建树结构需继承该基类
 * @author Administrator
 */
@Data
public class Node {

    private static final long serialVersionUID = 8875995344582620331L;
    private String id;
    /**
     * 父节id
     */
    private String parentId;
    /**
     * 父节点
     */
    private Node parent;
    /**
     * 层级
     */
    private int level;
    /**
     * 跟节点id
     */
    private String rootId;
    /**
     * 叶子节点
     */
    private boolean leaf;
    /**
     * 子节点list
     */
    private List<Node> children;

    public Node(){}
    public Node(String id, String parentId){
        this.setId(id);
        this.parentId = parentId;
    }
}
