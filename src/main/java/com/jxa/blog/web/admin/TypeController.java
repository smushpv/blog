package com.jxa.blog.web.admin;

import com.jxa.blog.po.Type;
import com.jxa.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /*
    * 需要做分页查询
    * 避免条件太多导致页面数据载入量过大
    * 使用Pageable pageable
    * 同时给pageable设置默认属性值
    * @PageableDefault
    * size指定每页条数
    * sort = {"id"}根据id排序
    * direction = Sort.Direction.DESC排序方式为倒序
    * */
    @GetMapping("/types")
    public String types(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable,Model model){
        model.addAttribute("page",typeService.listTypes(pageable));
        return "admin/types";
    }
    /*跳转到type新增页面
    * 添加后端验证时，需要创建一个Type对象
    * */
    @GetMapping("/types/to_add_type_page")
    public String toAddTypePage(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    /*新增一个type
    * 新增成功之后给予消息反馈用RedirectAttributes attributes
    * attributes.addFlashAttribute("","");
    * ---------------------------------------
    * Type类上通过注解的方式，添加校验
    * name不能为空
    * @NotBlank(message = "嘿嘿嘿！分类名称不能为空")
    *  private String name;//分类名称
    * 使用注解@NotBlank(message = "分类名称不能为空")
    * 如果name为空，后台会抛出异常
    * 在形参上添加注解@Valid
    * 同时，添加BindingResult result来接受后台message的消息
    * th:errors="*{name}"会取到@NotBlank(message = "嘿嘿嘿！分类名称不能为空")的消息
    *
    * */
    @PostMapping("/types")
    public String saveType(@Valid Type type,
                           BindingResult result,
                           RedirectAttributes attributes){

        /*业务上的错误
        * 添加新分类是，先查询是否已经存在，如果存在，则给出提示，不可以重复添加
        * 根据名称查询
        * */
        Type t = typeService.findOneByName(type.getName());
        /*不等于空时*/
        if(t!=null){
            result.rejectValue("name","nameError","千万不能重复添加");
        }
        /*如果result有错误，则返回新增页面（types-input.html）*/
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Type type1 = typeService.saveType(type);
        if (type1 != null){
            attributes.addFlashAttribute("message","oh~,新增成功了呦");
        }else{
            attributes.addFlashAttribute("message","oh~,数据库中没有加入数据");
        }
        return "redirect:/admin/types";
    }

    /*跳转到type的修改页面
    * @PathVariable此注解用来接收路径上的id
    * */
    @GetMapping("/types/{id}/edite")
    public String toEditePage(@PathVariable Long id, Model model){
        /*根据id查询到type*/
        model.addAttribute("type",typeService.getTypeById(id));
        return "admin/types-input";
    }

    /*type编辑
    * 第一部：先根据id查出需要编辑的type
    * 第二部：编辑后保存到数据库
    * @PathVariable 接收id,
    * */
    @PostMapping("/types/{id}")
    public String editeType(@Valid Type type,
                           BindingResult result,
                            @PathVariable Long id,
                           RedirectAttributes attributes){

        /*业务上的错误
         * 添加新分类是，先查询是否已经存在，如果存在，则给出提示，不可以重复添加
         * 根据名称查询
         * */
        Type t = typeService.findOneByName(type.getName());
        /*不等于空时*/
        if(t!=null){
            result.rejectValue("name","nameError","千万不能重复添加");
        }
        /*如果result有错误，则返回新增页面（types-input.html）*/
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Type type1 = typeService.updateType(id,type);
        if (type1 != null){
            attributes.addFlashAttribute("message","oh~,更新成功了呦呦，切克闹");
        }else{
            attributes.addFlashAttribute("message","oh~,数据库中没有发生变化呦呦，切克闹");
        }
        return "redirect:/admin/types";
    }

    /*根据id删除一个type
    * @PathVariable获取id值，传给形参
    * RedirectAttributes attributes
    * 前端接收信息
    * <div class="ui success message" th:unless="${#strings.isEmpty(message)}">
    * <i class="close icon"></i>
    *  <div class="header">提示：</div>
    *  <p th:text="${message}">恭喜，操作成功！</p>
    * */
    @GetMapping("/types/{id}/delete")
    public String deleteOneType(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteTypeById(id);
        attributes.addFlashAttribute("message","已经残忍的删除了");
        return "redirect:/admin/types";
    }



}
