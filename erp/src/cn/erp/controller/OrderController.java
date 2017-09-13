package cn.erp.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.erp.dto.GoodsDto;
import cn.erp.dto.GoodstypeDto;
import cn.erp.dto.OrderDetailDto;
import cn.erp.dto.OrderDto;
import cn.erp.dto.SelOrderByTimeAndCreaterDto;
import cn.erp.pojo.TblSupplier;
import cn.erp.service.EmpService;
import cn.erp.service.GoodsService;
import cn.erp.service.GoodstypeService;
import cn.erp.service.OrderService;
import cn.erp.service.SupplierService;
import cn.erp.util.PageResult;

/**
 * 订单
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private SupplierService supplierService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private EmpService empService;

	@Autowired
	private GoodstypeService goodstypeService;
	
	@Autowired
	private GoodsService goodsService;

	// 到采购订单列表
	@RequestMapping(value = "/orderList.do")
	public String orderList(ModelMap model, String creater, Long createtime1, Long createtime2, Integer ordertype,
			Integer totalnum1, Integer totalnum2, Double totalprice1, Double totalprice2) {

		List<OrderDto> list = orderService.selOrder(creater, createtime1, createtime2, ordertype, totalnum1, totalnum2,
				totalprice1, totalprice2);
		model.addAttribute("orderList", list);

		return "order/in/inList";
	}

	// 到新建订单，订单管理//待完善
	@RequestMapping(value = "/orderAdd.do")
	public String orderAdd(ModelMap model) {
		List<TblSupplier> supplierAll = supplierService.selAllSupplier();
		model.addAttribute("supplierAll", supplierAll);
		// 根据供应商查询出商品类别信息和商品信息
		//查出第一个供应商的商品种类以及第一个商品种类下的商品
		
		List<GoodstypeDto> goodsTypelist=null;
		if(supplierAll!=null&&supplierAll.size()>0){
		goodsTypelist = goodstypeService.selGoodstype(null, supplierAll.get(0).getUuid());
		}
		model.addAttribute("goodsTypelist", goodsTypelist);
		
		
		List<GoodsDto> goodsList =null;
		if(goodsTypelist!=null&&goodsTypelist.size()>0){
		goodsList = goodsService.selGoodsByTypeId(goodsTypelist.get(0).getUuid());
		}
		model.addAttribute("goodsList", goodsList);
		
		
		return "order/in/input2";
	}

	// 通过id查询商品类别
	@RequestMapping("/findGoodstype")
	public @ResponseBody List<GoodstypeDto> findGoodstype(Long gysuuid) {
		List<GoodstypeDto> list = goodstypeService.selGoodstype(null, gysuuid);
		return list;
	}

	// 通过商品种类idid查询商品
	@RequestMapping("/findGood")
	public @ResponseBody List<GoodsDto> findGood(Long gooduuid) {
		List<GoodsDto> list=goodsService.selGoodsByTypeId(gooduuid);
		return list;
	}

	// 到订单审核列表
	@RequestMapping(value = "/orderCheck.do")
	public String orderCheck(ModelMap model, String glyname, String createtimes, String createtimeends,PageResult pageResult)
			throws Exception {
		// List<TblOrder> orders = orderService.getAllOrder();
		// model.addAttribute("orders", orders);
		// 根据下单时间和下单人查询
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Long createtime = null;
		Long createtimeend = null;
		Long zdrname = null;// 制单人id
		if (StringUtils.isNotBlank(createtimes)) {
			createtime = dateFormat.parse(createtimes).getTime();

		}
		if (StringUtils.isNotBlank(createtimeends)) {
			createtimeend = dateFormat.parse(createtimeends).getTime();
		}
		if (StringUtils.isNotBlank(glyname)) {
			zdrname = empService.selempByName(glyname);
		}
		pageResult.setPageSize(8);
		List<SelOrderByTimeAndCreaterDto> orderDtos = orderService.selOrderByTimeAndCreater(createtime, createtimeend,
				zdrname,pageResult);
		model.addAttribute("orderDtos", orderDtos);

		// List<OrderDto> orderDtos = orderService.selOrderByOrderNum(uuid);
		// model.addAttribute("orderDtos", orderDtos);
		return "order/in/inApproveList";
	}

	// 到订单审核//待修改
	@RequestMapping(value = "/inApprove.do")
	public String inApprove(ModelMap model, Long uuid) {
		// 1一个订单有多个订单项,
		// 2查询出订单 ，在查询出订单详情
		// 查询出订单
		List<OrderDto> orderDtos = orderService.selOrderByOrderNum(uuid);
		OrderDto orderDto = new OrderDto();
		if (orderDtos != null) {
			orderDto = orderDtos.get(0);
		}
		model.addAttribute("orderDto", orderDto);
		// 查询出订单详情订单明细//待修改sql
		List<OrderDetailDto> orderDetailDtos = orderService.selOrderDetailById(uuid);
		model.addAttribute("orderDetailDtos", orderDetailDtos);

		// 查询出单价和总数
		// List<OrderNumPriceDto> OrderNumPriceDto =
		// orderService.selNumAndPriceById(uuid);
		return "order/in/inApprove";
	}

	// @RequestMapping(value="/selOrder.do")
	// public

}
