package com.icss.oa.meeting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.meeting.dao.MeetingMapper;
import com.icss.oa.meeting.dao.MeetingRoomMapper;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.system.pojo.Department;

@Service
@Transactional(rollbackFor=Exception.class)
public class MeetingRoomService {
	
	@Autowired
	private MeetingRoomMapper mapper;
	
	@Autowired
	private MeetingMapper meetingMapper;

	/**
	 *增加会议
	 */
	public void addMee(MeetingRoom mee) {
		mapper.insert(mee);
	}
	
	public void deleteMee(Integer meeId){
		mapper.delete(meeId);
	}
	
	public void updateMee(MeetingRoom mee){
		mapper.update(mee);
	}
	
	/**
	 * 分页查询会议
	 */
	@Transactional(readOnly=true)
	public List<MeetingRoom> queryMeeByPage(Pager pager) {
		return mapper.queryByPage(pager.getStart(), pager.getPageSize());
	}	
	
	@Transactional(readOnly=true)
	public List<MeetingRoom> query(){
		return mapper.query();
	}
	
	@Transactional(readOnly=true)
	List<MeetingRoom> queryByPage(Integer start,Integer pageSize){
		return queryByPage(start, pageSize);
	}
	
	@Transactional(readOnly=true)
	List<MeetingRoom> queryByCondition(Integer start,Integer pageSize,String roomPlace,Boolean isReservation,Integer roomSize,Boolean hasAirConditoning){
		return queryByCondition(start, pageSize, roomPlace, isReservation, roomSize, hasAirConditoning);
	}
	
	@Transactional(readOnly=true)
	int getCountByCondition(String roomPlace,Boolean isReservation,Integer roomSize,Boolean hasAirConditoning){
		return getCountByCondition(roomPlace, isReservation, roomSize, hasAirConditoning);
	}


	@Transactional(readOnly=true)
	public MeetingRoom queryRoomById(Integer roomId) {
		return mapper.queryById(roomId);
	}
	
	@Transactional(readOnly=true)
	public Integer getSize(Integer roomId){
		MeetingRoom room=mapper.queryById(roomId);
		return room.getRoomSize();
	}

	@Transactional(readOnly=true)
	public List<Meeting> queryMeeByRoomId(Integer roomId){
		return meetingMapper.queryMeeByRoomId(roomId);
	}
	
	@Transactional(readOnly=true)
	public Meeting lastMeeByRoom(Integer roomId){
		return meetingMapper.lastMeeByRoom(roomId, "审批通过");
	}
}