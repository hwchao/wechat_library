package com.library.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.common.pojo.EUTreeNode;
import com.library.common.pojo.TaotaoResult;
import com.library.mapper.BookCateMapper;
import com.library.pojo.BookCate;
import com.library.pojo.BookCateExample;
import com.library.pojo.BookCateExample.Criteria;
import com.library.service.BookCateService;


/**
 * 内容分类管理
 */
@Service
public class BookCateServiceImpl implements BookCateService {

	@Autowired
	private BookCateMapper bookCateMapper;
	
	/**
	 * 查询分类节点信息
	 */
	@Override
	public List<EUTreeNode> getCategoryList(Integer parentId) {
		//根据parentid查询节点列表
		BookCateExample example = new BookCateExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<BookCate> list = bookCateMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		for (BookCate bookCate : list) {
			//创建一个节点
			EUTreeNode node = new EUTreeNode();
			node.setId(bookCate.getId());
			node.setText(bookCate.getCateName());
			node.setState(bookCate.getIsParent()?"closed":"open");
			
			resultList.add(node);
		}
		return resultList;
	}
	/**
	 * 添加新的节点
	 */
	@Override
	public TaotaoResult insertContentCategory(Integer parentId, String name) {
		//创建一个pojo
		BookCate bookCate = new BookCate();
		bookCate.setCateName(name);
		bookCate.setIsParent(false);
		//'状态。可选值:1(正常),2(删除)',
		bookCate.setState(false);
		bookCate.setParentId(parentId);
		bookCate.setCreateTime(new Date());
		bookCate.setUpdateTime(new Date());
		//添加记录
		bookCateMapper.insert(bookCate);
		//查看父节点的isParent列是否为true，如果不是true改成true
		BookCate parentCat = bookCateMapper.selectByPrimaryKey(parentId);
		//判断是否为true
		if(!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			//更新父节点
			bookCateMapper.updateByPrimaryKey(parentCat);
		}
		//返回结果
		return TaotaoResult.ok(bookCate);
	}
	@Override
	public TaotaoResult updateBookCate(Integer id, String name) {
		BookCate bookCate = new BookCate();
		bookCate.setId(id);
		bookCate.setCateName(name);
		bookCateMapper.updateByPrimaryKeySelective(bookCate);
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult deleteBookCate(Integer parentId, Integer id) {
		
		BookCate bookCate = new BookCate();
		bookCate.setIsParent(false);
		bookCateMapper.updateByPrimaryKeySelective(bookCate);
		return TaotaoResult.ok();
	}
	/**
	 * 
	 * 功能：递归删除所有子节点
	 * 作者：hwchao
	 * 修改时间：2017年8月23日下午9:13:27
	 */
	private void deleteByParentId(){
		
	}

}

