package cn.wolfcode.trip.admin.web.controller;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 供系统内部员工来访问
 */
@Controller
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private IRegionService service;

    @RequestMapping("/list")
    public String list() {

        return "region/list";
    }

    @RequestMapping("/getRegionsMapByParentId")
    @ResponseBody
    public List<Map<String, Object>> getRegionsMapByParentId(Model model, Long parentId) {
        List<Region> list = service.getRegionsMapByParentId(parentId);

        List<Map<String, Object>> datas = new ArrayList<>();


        //将数据转换成插件需要的格式
        for (Region region : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("text", region.getName());
            map.put("id", region.getId());
            map.put("lazyLoad", true);
            if (region.getState() == 1) {
                map.put("tags", new String[]{"推荐"});
            }
            datas.add(map);

        }
        return datas;
    }
    @RequestMapping("/getRegionsByParentId")
    @ResponseBody
    public List<Region> getRegionsByParentId(Model model, Long parentId) {
        List<Region> list = service.getRegionsMapByParentId(parentId);
        return list;
    }
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Region region) {
        service.saveOrUpdate(region);
        //URL重定向到list方法中
        return "redirect:/region/list.do";
    }
    @RequestMapping("/updateState")
    public String updateState(Long id,int state) {
        service.updateState(id,state);
        //URL重定向到list方法中
        return "redirect:/region/list.do";
    }

}
