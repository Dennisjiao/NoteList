package spittr.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.Spittle;
import spittr.data.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

	private static final String MAX_LONG_AS_STRING = "9223372036854775807";

	@Autowired
	private SpittleRepository spittleRepository;
	
	
	/*
	 * @RequestParam注解将请求参数绑定至方法参数。GET和POST请求传的参数会自动转换赋值到@RequestParam 所注解的变量上
	 * @RequestParam 有三个属性：
	 * （1）value：请求参数名（必须配置）
	 * （2）required：是否必需，默认为 true，即 请求中必须包含该参数，如果没有包含，将会抛出异常（可选配置）
	 * （3）defaultValue：默认值，如果设置了该值，required 将自动设为 false，无论你是否配置了required，配置了什么值，都是 false（可选配置）
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Spittle> spittles(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "count", defaultValue = "20") int count) {
		return spittleRepository.findSpittles(max, count);
	}
	
	/*RequestMapping是一个用来处理请求地址映射的注解。{spittleId}是URL中的占位符参数。
	 * 
	 *例如：对于url="/spittles/2"，则"/{spittleId}"中的spittleId对应的值就是2
	 *
	 *通过 @PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入参中：URL 中的 {xxx} 占位符
	 * 可以通过@PathVariable("xxx") 绑定到操作方法的入参中。
	 */
	@RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
	public String spittle(@PathVariable("spittleId") long Id, Model model) {
		model.addAttribute(spittleRepository.findOne(Id));
		return "spittle";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveSpittle(SpittleForm form, Model model) throws Exception {
		spittleRepository.save(new Spittle(null, form.getMessage(), new Date(), 
				form.getLongitude(), form.getLatitude()));
		
		return "redirect:/spittles";
	}

}
