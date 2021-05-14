package kr.or.connect.guestbook.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.guestbook.dao.GuestbookDao;
import kr.or.connect.guestbook.dao.LogDao;
import kr.or.connect.guestbook.dto.Guestbook;
import kr.or.connect.guestbook.dto.Log;
import kr.or.connect.guestbook.service.GuestbookService;

@Service //Service Layer라는 것을 알려줌
public class GuestbookServiceImpl implements GuestbookService {
	@Autowired
	GuestbookDao guestbookDao;
	
	@Autowired
	LogDao logDao;

	@Override
	@Transactional //내부적으로 readOnly라는 형태로 connection 사용
	public List<Guestbook> getGuestbooks(Integer start) {
		List<Guestbook> list = guestbookDao.selectAll(start, GuestbookService.LIMIT);
		return list;
	} //페이징 처리했을 때 시작 부터 5페이지만 보여주는 메소드

	@Override
	@Transactional(readOnly=false) //readOnly가 아니니까..
	public int deleteGuestbook(Long id, String ip) {
		int deleteCount = guestbookDao.deleteById(id);
		Log log = new Log(); //삭제하고 log에 추가
		log.setIp(ip);
		log.setMethod("delete");
		log.setRegdate(new Date());
		logDao.insert(log);
		return deleteCount;
	}

	@Override
	@Transactional(readOnly=false)
	public Guestbook addGuestbook(Guestbook guestbook, String ip) {
		guestbook.setRegdate(new Date());
		Long id = guestbookDao.insert(guestbook);
		guestbook.setId(id); //Log부분에 입력하기 위해
		
//		if(1 == 1)
//		throw new RuntimeException("test exception");
//		만약 중간에 여기서 오류가 발생한다면, 앞에 수행됐던 guestbook insert도 취소됨. 트랜잭션의 특징
//		전체 메소드가 성공해야 실행됨
		
		Log log = new Log(); //log에 추가
		log.setIp(ip);
		log.setMethod("insert");
		log.setRegdate(new Date());
		logDao.insert(log);
		
		return guestbook;
	} 

	@Override
	public int getCount() {
		return guestbookDao.selectCount();
	}
	
}
