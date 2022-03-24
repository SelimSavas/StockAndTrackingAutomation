package tr.com.bgss.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.bgss.complexcontract.StokContractComplex;
import tr.com.bgss.complexcontract.StokTotalContractComplex;
import tr.com.bgss.contract.KategoriContract;
import tr.com.bgss.contract.StokContract;
import tr.com.bgss.core.ObjectHelper;
import tr.com.bgss.interfaces.DALInterfaces;

public class StokDAL extends ObjectHelper implements DALInterfaces<StokContract> {

	@Override
	public void Insert(StokContract entity) {

		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement
					.executeUpdate("INSERT INTO Stok (UrunId, PersonelId, Tarih, Adet) VALUES(" + entity.getUrunId()
							+ "," + entity.getPersonelId() + ",'" + entity.getTarih() + "'," + entity.getAdet() + ")");
			statement.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
/*
 * SELECT stok.Id, personel.AdiSoyadi, urunler.Adi, Adet, stok.Tarih FROM stok LEFT JOIN urunler on stok.UrunId = urunler.Id LEFT JOIN personel on stok.PersonelId = personel.Id
 */
	public List<StokContractComplex> GetAllStok(){
		
List<StokContractComplex> datacontract = new ArrayList<StokContractComplex>();
		
		Connection connection = getConnection();
		StokContractComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT stok.Id, personel.AdiSoyadi, urunler.Adi, Adet, stok.Tarih FROM stok LEFT JOIN urunler on stok.UrunId = urunler.Id LEFT JOIN personel on stok.PersonelId = personel.Id ORDER BY  stok.Id DESC");
			while(resultSet.next()) {
				contract = new StokContractComplex();
				contract.setId(resultSet.getInt("stok.Id"));
				contract.setPersonelAdi(resultSet.getString("personel.AdiSoyadi"));
				contract.setUrunAdi(resultSet.getString("urunler.Adi"));
				contract.setAdet(resultSet.getInt("Adet"));
				contract.setTarih(resultSet.getString("stok.Tarih"));
				
				datacontract.add(contract);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return datacontract;
		
	}
	
	public List<StokTotalContractComplex> GetTotalStok(){
		
		List<StokTotalContractComplex> datacontract = new ArrayList<StokTotalContractComplex>();
				
				Connection connection = getConnection();
				StokTotalContractComplex contract;
				try {
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT SUM(ADET) as toplam, stok.Id, personel.AdiSoyadi, urunler.Adi, Adet, stok.Tarih FROM stok LEFT JOIN urunler on stok.UrunId = urunler.Id LEFT JOIN personel on stok.PersonelId = personel.Id GROUP BY urunId");
					while(resultSet.next()) {
						contract = new StokTotalContractComplex();
						contract.setId(resultSet.getInt("stok.Id"));
						contract.setPersonelAdi(resultSet.getString("personel.AdiSoyadi"));
						contract.setUrunAdi(resultSet.getString("urunler.Adi"));
						contract.setAdet(resultSet.getInt("Adet"));
						contract.setTarih(resultSet.getString("stok.Tarih"));
						contract.setToplam(resultSet.getInt("toplam"));
						
						datacontract.add(contract);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return datacontract;
				
			}
	@Override
	public List<StokContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Delete(StokContract entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void Update(StokContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<StokContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
