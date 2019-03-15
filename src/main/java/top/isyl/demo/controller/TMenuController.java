package top.isyl.demo.controller;


import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.isyl.demo.entity.TMenu;
import top.isyl.demo.entity.TMenuNode;
import top.isyl.demo.service.ITMenuService;
import top.isyl.demo.util.TreeBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author huangyunlong
 * @since 2019-03-12
 */
@RestController
@RequestMapping("/t-menu")
public class TMenuController {



    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ITMenuService menuService;



    /**
     * 获取菜单列表
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/menuList")
    @ApiOperation(value = "获取菜单列表")
    public Object menuList( ) throws Exception {
        log.info("获取菜单列表");
        List<TMenu> menuList = menuService.list();

        //获取科目及其子科目   转成Tree结构
        TreeBuilder treeBuilder = new TreeBuilder();
        List<TMenuNode> actSubjectNodeVos = new ArrayList<>();
        menuList.forEach(x -> {
            actSubjectNodeVos.add(new TMenuNode(x));
        });
        List<TMenuNode> treeList = (List<TMenuNode>) treeBuilder.buildListToTree(actSubjectNodeVos);

        return treeList;
    }

}
