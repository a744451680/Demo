package top.isyl.demo.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import top.isyl.demo.entity.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class TreeBuilder {


    /**
     * 转树
     * @param dirs
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<? extends Node> buildListToTree(List<? extends Node> dirs) {
        List<Node> roots = findRoots(dirs);


        List<Node> notRoots = (List<Node>) CollectionUtils.subtract(dirs, roots);

        List<? extends Node> tree = getTree(roots, notRoots);

        tree.forEach(x -> {
            List<Node> children = x.getChildren();
            if(children == null || children.size() == 0){
                x.setLeaf(true);
            }
        });

        return tree;
    }

    /**
     * 获得树形结构
     * @param rootList 根节点
     * @param bodyList 其他节点，也可以包含根节点
     * @return
     */
    public List<? extends Node> getTree(List<? extends Node> rootList,List<? extends Node> bodyList){   //调用的方法入口
        if(bodyList != null && !bodyList.isEmpty()){
            //声明一个map，用来过滤已操作过的数据
            Map<String,String> map = Maps.newHashMapWithExpectedSize(bodyList.size());
            rootList.forEach(beanTree -> getChild(bodyList,beanTree,map));
        }
        return rootList;
    }

    public void getChild(List<? extends Node> bodyList,Node beanTree,Map<String,String> map){
        List<Node> childList = Lists.newArrayList();
        bodyList.stream()
                .filter(c -> !map.containsKey(c.getId()))
                .filter(c -> c.getParentId().equals(beanTree.getId()))
                .forEach(c ->{
                    map.put(c.getId(),c.getParentId());
                    getChild(bodyList,c,map);
//                    c.setParent(beanTree);  // 避免内存溢出，想获取父节点  可将list转成map获取
                    childList.add(c);
                });
        if(childList.size() == 0){
            beanTree.setLeaf(true);
        }
        beanTree.setChildren(childList);
    }

    /**
     * 查找所有跟节点
     * @param allNodes
     * @return
     */
    private List<Node> findRoots(List<? extends Node> allNodes) {
        List<Node> results = new ArrayList<Node>();
        for (Node node : allNodes) {
            boolean isRoot = true;
            for (Node comparedOne : allNodes) {
                if (StringUtils.isNotBlank(node.getParentId()) && node.getParentId().equals(comparedOne.getId())) {
                    isRoot = false;
                    break;
                }
            }
            if (isRoot) {
                node.setLevel(0);
                results.add(node);
                node.setRootId(node.getId());
            }
        }
        return results;
    }

    @SuppressWarnings("unchecked")
    private List<Node> findChildren(Node root, List<Node> allNodes) {
        List<Node> children = new ArrayList<>();

        for (Node comparedOne : allNodes) {
            if (StringUtils.isNotBlank(comparedOne.getParentId()) && comparedOne.getParentId().equals(root.getId())) {
                comparedOne.setParent(root);
                comparedOne.setLevel(root.getLevel() + 1);
                children.add(comparedOne);
            }
        }
        List<Node> notChildren = (List<Node>) CollectionUtils.subtract(allNodes, children);
        for (Node child : children) {
            List<Node> tmpChildren = findChildren(child, notChildren);
            if (tmpChildren == null || tmpChildren.size() < 1) {
                child.setLeaf(true);
            } else {
                child.setLeaf(false);
            }
            child.setChildren(tmpChildren);
        }
        return children;
    }

    public List<? extends Node> getLeafChildren(List<Node> resultList, List<? extends Node> children) {

        if(children != null){

            for (Node node : children) {
                if (node.isLeaf()) {
                    resultList.add(node);
                } else {
                    getLeafChildren(resultList, node.getChildren());
                }
            }
        }
        return resultList;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args)  {

/*        //获取科目及其子科目   转成Tree结构
        TreeBuilder treeBuilder = new TreeBuilder();
        List<TMenuNode> actSubjectNodeVos = new ArrayList<>();
        menuList.forEach(x -> {
            actSubjectNodeVos.add(new TMenuNode(x));
        });
        List<TMenuNode> treeList = (List<TMenuNode>) treeBuilder.buildListToTree(actSubjectNodeVos);*/


//        TreeBuilder tb = new TreeBuilder();
//        List<Node> allNodes = new ArrayList<>();
//
//        allNodes.add(new Dictionary("1", "0", "001", "节点001", 0));
//        allNodes.add(new Dictionary("2", "0", "002", "节点002", 0));
//        allNodes.add(new Dictionary("3", "0", "003", "节点003", 0));
//        allNodes.add(new Dictionary("4", "1", "004", "节点004", 0));
//        allNodes.add(new Dictionary("5", "1", "005", "节点005", 0));
//        allNodes.add(new Dictionary("6", "1", "006", "节点006", 0));
//        allNodes.add(new Dictionary("7", "4", "007", "节点007", 0));
//        allNodes.add(new Dictionary("8", "4", "008", "节点008", 0));
//        allNodes.add(new Dictionary("9", "5", "009", "节点009", 0));
//        allNodes.add(new Dictionary("10", "5", "010", "节点010", 0));
//        allNodes.add(new Dictionary("11", "7", "011", "节点011", 0));
//        allNodes.add(new Dictionary("12", "7", "012", "节点012", 0));
//
//        // 显示所有节点
//        List<Node> roots = (List<Node>) tb.buildListToTree(allNodes);
//        for (Node n : roots) {
////            System.out.println(JSON.toJSONString(n));
//        }
//
//        // 查找所有子节点
//        List<Node> children = tb.findChildren(new Dictionary("1", "0"), allNodes);
//        for (Node n : children) {
////            System.out.println(JSON.toJSONString(n));
//        }
//        // 查找所有叶子节点
//        System.out.println("------------------");
//        List<Node> resultList = tb.getLeafChildren(new ArrayList<Node>(), children);
//        System.out.println("所有叶子节点===============》");
//        for (Node n : resultList) {
//            System.out.println(JSON.toJSONString(n));
//        }
    }
}
