package com.huatek.busi.service.phicom.game;

import java.util.List;

import com.huatek.busi.dto.phicom.game.PhiGameDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiGameService {
	
	/** 
	* @Title: savePhiGameDto 
	* @Description: 保存PhiGame信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiGameDto(PhiGameDto entityDto) ;

	
	/** 
	* @Title: deletePhiGame 
	* @Description:  删除PhiGame信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiGame(Long id) ;

	
	/** 
	* @Title: getPhiGameDtoById 
	* @Description: 获取PhiGame的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiGameDto getPhiGameDtoById(Long id);

	
    /** 
	* @Title: updatePhiGame 
	* @Description:  更新PhiGame信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiGame(Long id, PhiGameDto entityDto) ;
	
	
    //通过类型找到对应的游戏
	PhiGameDto  getPhiGameDtoByTypeForApp(String type,Long memberId);
	
	
	//玩大转盘
	Object  playGamesDOnceForApp(Long memberId,String type,String location);
	
	
	//玩九宫格
	Object  playGamesJOnceForApp(Long memberId,String type,String location);
	/** 
	* @Title:  getAllPhiGamePage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiGameDto>    
	*/ 
	DataPage<PhiGameDto> getAllPhiGamePage(QueryPage queryPage);
	
	 
	/** 
	* @Title:  getAllPhiGameDto 
	* @Description: 获取所有的PhiGame
	* @param      
	* @return  List<PhiGameDto>    
	* @throws 
	*/
	List<PhiGameDto> getAllPhiGameDto();


	void editPhiScoreswitch(Long id, String taskSwitch);
	

	
}
