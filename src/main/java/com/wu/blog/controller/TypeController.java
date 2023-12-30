package com.wu.blog.controller;

import com.wu.blog.pojo.Type;
import com.wu.blog.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class TypeController {

    @Autowired
    private TypeService typeService;


    @ApiOperation(value = "查找所有分类",notes = "查找所有分类")
    @GetMapping("/types")
    public List<Type> list() {
        return typeService.listTypes();
    }

    @ApiOperation(value = "查找父类",notes="查找父类")
    @GetMapping("/parentTypes")
    public List<Type> listParentTypes() {
        return typeService.listParentTypes();
    }

    @ApiOperation(value = "添加分类",notes="添加分类")
    @PostMapping("/type")
    public Map<String,String> addType(@RequestBody Type type) {
        Type t = typeService.findTypeById(type.getTypeId());
        Map<String,String> map=new HashMap<>();
        if(t!=null) {
            map.put("status","fail");
            map.put("message","不能输入已经存在的分类");
        } else {
            Integer i = typeService.addType(type);
            if(i==null) {
                map.put("status","fail");
                map.put("message","新增失败");
            }else {
                map.put("status","success");
                map.put("message","新增成功");
            }
        }
        return map;
    }

    @ApiOperation(value="删除分类",notes = "删除分类")
    @DeleteMapping("/types/{typeId}")
    public void deleteType(@PathVariable("typeId") Integer typeId) {
        typeService.deleteTypeById(typeId);
    }

    @ApiOperation(value = "更新分类",notes="更新分类")
    @PutMapping("/type")
    public  Map<String,String> updateType(@RequestBody Type type) {
        Type t = typeService.findTypeById(type.getTypeId());
        Map<String,String> map=new HashMap<>();
        if(t!=null&&t.getTypeName().equals(type.getTypeName())) {
            map.put("status","fail");
            map.put("message","不能修改为已经存在的分类");
        } else {
            Integer i = typeService.updateType(type);
            if(i==null) {
                map.put("status","fail");
                map.put("message","修改失败");
            }else {
                map.put("status","success");
                map.put("message","修改成功");
            }
        }
        return map;
    }

    @ApiOperation(value = "根据id查找Type对象",notes = "根据id查找Type对象")
    @GetMapping("/type/{typeId}")
    public Type findType(@PathVariable("typeId") Integer typeId) {
        return typeService.findTypeById(typeId);
    }

    @ApiOperation(value = "分页查询Types对象",notes = "分页查询Type对象")
    @GetMapping("types/{pageSize}/{size}")
    public List<Type> findTypesOfPages(@PathVariable("pageSize")Integer pageSize,@PathVariable("size")Integer size) {
        Integer begin=Integer.valueOf((pageSize-1)*size);
        return typeService.findTypesOfPages(begin,size);
    }

    @ApiOperation(value = "根据name搜索Type",notes = "根据name搜素Type")
    @GetMapping(value={"/type/search/{typeName}","/type/search"})
    public List<Type> findTypesByTypeName(@PathVariable(value="typeName" ,required = false) String typeName) {
        if(typeName!=null){
            String name="%"+typeName+"%";
            return typeService.findTypesByTypeName(name);
        } else {
            return typeService.listTypes();
        }
    }
}
