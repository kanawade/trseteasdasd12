package com.hrdb.service;
// Generated 6 Sep, 2014 1:08:49 PM

import com.wavemaker.runtime.data.expression.QueryFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrdb.*;
import com.wavemaker.runtime.data.dao.*;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;

/**
 * ServiceImpl object for domain model class Vacation.
 * @see com.hrdb.Vacation
 */
@Service("hrdb.VacationService")
public class VacationServiceImpl implements VacationService {


    private static final Logger LOGGER = LoggerFactory.getLogger(VacationServiceImpl.class);


@Autowired
@Qualifier("hrdb.VacationDao")
private WMGenericDao<Vacation, Integer> wmGenericDao;
  public void setWMGenericDao(WMGenericDao<Vacation, Integer> wmGenericDao){
          this.wmGenericDao = wmGenericDao;
  }

    @Transactional(value = "hrdbTransactionManager")
    @Override
    public Vacation create(Vacation vacation) {
        LOGGER.debug("Creating a new vacation with information: {}" , vacation);
        return this.wmGenericDao.create(vacation);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class, value = "hrdbTransactionManager")
    @Override
    public Vacation delete(Integer vacationId) throws EntityNotFoundException {
        LOGGER.debug("Deleting vacation with id: {}" , vacationId);
        Vacation deleted = this.wmGenericDao.findById(vacationId);
        if (deleted == null) {
            LOGGER.debug("No vacation found with id: {}" , vacationId);
            throw new EntityNotFoundException(String.valueOf(vacationId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(readOnly = true, value = "hrdbTransactionManager")
    @Override
    public Page<Vacation> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all vacations");
        return this.wmGenericDao.search(queryFilters, pageable);
    }
    
    @Transactional(readOnly = true, value = "hrdbTransactionManager")
    @Override
    public Page<Vacation> findAll(Pageable pageable) {
        LOGGER.debug("Finding all vacations");
        return this.wmGenericDao.search(null, pageable);
    }

    @Transactional(readOnly = true, value = "hrdbTransactionManager")
    @Override
    public Vacation findById(Integer id) throws EntityNotFoundException {
        LOGGER.debug("Finding vacation by id: {}" , id);
        Vacation vacation=this.wmGenericDao.findById(id);
        if(vacation==null){
            LOGGER.debug("No vacation found with id: {}" , id);
            throw new EntityNotFoundException(String.valueOf(id));
        }
        return vacation;
    }

    @Transactional(rollbackFor = EntityNotFoundException.class, value = "hrdbTransactionManager")
    @Override
    public Vacation update(Vacation updated) throws EntityNotFoundException {
        LOGGER.debug("Updating vacation with information: {}" , updated);
        this.wmGenericDao.update(updated);
        return this.wmGenericDao.findById((Integer)updated.getId());
    }

    @Transactional(readOnly = true, value = "hrdbTransactionManager")
    @Override
    public long countAll() {
        return this.wmGenericDao.count();
    }
}


