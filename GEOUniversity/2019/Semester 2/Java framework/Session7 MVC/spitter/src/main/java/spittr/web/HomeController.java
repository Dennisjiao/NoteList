package spittr.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @Controller 只是定义了一个控制器类
 * 在SpringMVC 中，控制器Controller 负责处理由DispatcherServlet 分发的请求，它把用户请求的数据经过业务处理层处理之后
 * 封装成一个Model ，然后再把该Model 返回给对应的View 进行展示。在SpringMVC 中提供了一个非常简便的定义Controller 的方法，
 * 你无需继承特定的类或实现特定的接口，只需使用@Controller 标记一个类是Controller ，然后使用@RequestMapping 和@RequestParam 
 * 等一些注解用以定义URL请求和Controller方法之间的映射，这样的Controller 就能被外界访问到。
 */
@Controller

/*
 * RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
 * RequestMapping注解有六个属性，下面我们把她分成三类进行说明。
 * 1、 value， method；
	value：   指定请求的实际地址，指定的地址可以是URI Template 模式；
	method：  指定请求的method类型， GET、POST、PUT、DELETE等；

	2、 consumes，produces；
	consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
	produces:  指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；

	3、 params，headers；
	params： 指定request中必须包含某些参数值的才让该方法处理。
	headers：指定request中必须包含某些指定的header值，才能让该方法处理请求。
 *
 *例如：
 *@RequestMapping("/appointments")
 *@RequestMapping(value="/new", method = RequestMethod.GET)
 *@RequestMapping(value = "/pets", method = RequestMethod.POST, consumes="application/json")，方法仅处理request Content-Type为“application/json”类型的请求。
 *@RequestMapping(value = "/pets/{petId}", method = RequestMethod.GET, produces="application/json")，方法仅处理request请求中Accept头中包含了"application/json"的请求，同时暗示了返回的内容类型为application/json;
 *@RequestMapping(value = "/pets/{petId}", method = RequestMethod.GET, params="myParam=myValue")，仅处理请求中包含了名为“myParam”，值为“myValue”的请求；
 */
@RequestMapping("/")
public class HomeController {

  @RequestMapping(method = GET)
  public String home(Model model) {
    return "home";
  }

}
