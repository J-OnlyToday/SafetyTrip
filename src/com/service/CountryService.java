package com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dao.MySqlSessionFactory;
import com.dto.CountryDTO;
import com.exception.MyException;

public class CountryService {

		public void countryInsert(CountryDTO dto) throws MyException {
			SqlSession session = MySqlSessionFactory.getSession();
			
			try {
				int n = session.insert("CountryMapper.countryInsert", dto);
				
				if(n != 1) {
					session.rollback();
					throw new MyException("국가 추가 에러");
				} else {
					session.commit();
				}
			} catch(Exception e) {
				e.printStackTrace();
				throw new MyException("국가 추가 에러");
			} finally {
				session.close();
			}
		}
		
		public CountryDTO countrySelectOneByCname(String cname) throws MyException {
			SqlSession session = MySqlSessionFactory.getSession();
			CountryDTO dto = null;
			
			try {
				dto = session.selectOne("CountryMapper.countrySelectOneByCname", cname);
			} catch(Exception e) {
				e.printStackTrace();
				throw new MyException("국가 검색 에러");
			} finally {
				session.close();
			}
			
			return dto;
		}
		
		public List<CountryDTO> countrySelectListByCname(HashMap<String, Object> map) throws MyException {
			SqlSession session = MySqlSessionFactory.getSession();
			List<CountryDTO> dto = null;
			
			try {
				dto = session.selectList("CountryMapper.countrySelectListByCname", map);
			} catch(Exception e) {
				e.printStackTrace();
				throw new MyException("국가 검색 에러");
			} finally {
				session.close();
			}
			
			return dto;
		}
		
		public List<CountryDTO> countrySelectListByCename(HashMap<String, Object> map) throws MyException {
			SqlSession session = MySqlSessionFactory.getSession();
			List<CountryDTO> dto = null;
			
			try {
				dto = session.selectList("CountryMapper.countrySelectListByCename", map);
			} catch(Exception e) {
				e.printStackTrace();
				throw new MyException("국가 검색 에러");
			} finally {
				session.close();
			}
			
			return dto;
		}
}