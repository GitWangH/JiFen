package com.huatek.frame.config;

import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

import com.huatek.esb.msg.MsgContainerService;


public class HuatekTransactionManager extends HibernateTransactionManager {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MsgContainerService msgContainerService;
	
	public void setMsgContainerService(MsgContainerService msgContainerService) {
		this.msgContainerService = msgContainerService;
	}
	@Override
	protected void doCommit(DefaultTransactionStatus status){
		msgContainerService.saveTransactionMessage();
		msgContainerService.reloadTransactionMessage();
		super.doCommit(status);
		msgContainerService.submitMessage(true);
	}
	@Override
	protected void doRollback(DefaultTransactionStatus status) {
		try{
			super.doRollback(status);
		}finally{
			msgContainerService.reloadTransactionMessage();
			msgContainerService.submitMessage(false);
		}
	}
}
