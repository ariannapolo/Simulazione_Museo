package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Exhibition;

public class ArtsmiaDAO {

	public List<ArtObject> listObject() {
		
		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Integer> getYearsExhibitions(){
		String sql = "SELECT DISTINCT begin from exhibitions";

		List<Integer> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getInt("begin"));
				
			}

			conn.close();
			Collections.sort(result);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<Exhibition> getExhibition(int anno){
		String sql = "select * from exhibitions where begin>= ?";

		List<Exhibition> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Exhibition(res.getInt("exhibition_id"),res.getString("exhibition_department"),res.getString("exhibition_title"),
						res.getInt("begin"),res.getInt("end")));
				
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Exhibition numOggettiMax(Map<Integer,Exhibition> map){
		String sql = "select exhibition_id,count(object_id) as num  from exhibition_objects group by exhibition_id";

		int maxNum = 0;
		Exhibition eMax =null;

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				int id = res.getInt("exhibition_id");
				if(map.get(id)!=null){
					map.get(id).setNumObject(res.getInt("num"));
					if(res.getInt("num")>=maxNum){
						maxNum = res.getInt("num");
						eMax = map.get(id);
						
					}
				}
				
				
			}

			conn.close();
			return eMax;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	public void setOggetti(Map<Integer,Exhibition> map){
		String sql = "select exhibition_id, object_id from exhibition_objects group by exhibition_id";

		
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				int id = res.getInt("exhibition_id");
				if(map.get(id)!=null){
					map.get(id).addObject(res.getInt("object_id"));;
				}
				
			}

			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
	}
}
