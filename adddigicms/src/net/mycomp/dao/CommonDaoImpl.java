package net.mycomp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import net.mycomp.persist.CmsContent;
import net.mycomp.persist.Portal;

public class CommonDaoImpl  extends NamedParameterJdbcTemplate implements ICommonDao {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(CommonDaoImpl.class);

	@PersistenceContext(unitName = "entityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	public CommonDaoImpl(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public List<CmsContent> findAllEnabledCmsContent() {
		TypedQuery<CmsContent> query = entityManager.
				createNamedQuery("CmsContent.findAllEnableCmsContent",CmsContent.class);
		query.setParameter("status", true);
		return  query.getResultList();
		}

	@Override
	public List<Portal> findAllEnabledPortal() {
		TypedQuery<Portal> query = entityManager.
				createNamedQuery("Portal.findAllEnablePortal",Portal.class);
		query.setParameter("status", true);
		return  query.getResultList();
	}

	@Override
	@Transactional
	public boolean saveObject(Object object) {
	 	entityManager.persist(object);
	 	return true;
	}
}
