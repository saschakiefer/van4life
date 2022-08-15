package de.saschakiefer.van4life.persistence.mapper;

import de.saschakiefer.van4life.domain.vo.Visit;
import de.saschakiefer.van4life.persistence.dao.VisitDao;

public class VisitMapper {
	public static Visit visitDataToDomain(VisitDao visitDao) {
		return new Visit(visitDao.getId(), visitDao.getDate());
	}
}
