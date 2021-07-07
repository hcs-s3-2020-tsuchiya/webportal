package jp.ac.hcs.s3a216.gourmet;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * グルメ検索機能の操作をする
 * グルメ情報APIを活用
 *
 */

@Transactional
@Service
public class GourmetService {
	
	@Autowired
	RestTemplate restTemplate;
	
	/** グルメサーチAPI リクエストURL */
	private static final String URL ="http://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key={API_KEY}&name={shopname}&large_service_area={large_service_area}&format=json";

	private static final Object API_KEY = "d2f83f5df85a3821";
	/**
	 * 指定したグルメサーチ情報を取得する。
	 */
	public ShopEntity getShops(String shopname,String large_service_area) {
		//APIにアクセスして、結果を取得
		String json = restTemplate.getForObject(URL, String.class,API_KEY,shopname,large_service_area);
		
		//エンティティクラスを生成する
				ShopEntity shopEntity = new ShopEntity();
				shopEntity.setShopname(shopname);
				
				//jsonクラスへの変換失敗のため、例外処理
				try {
					//変換クラスを生成し、文字列からjsonクラスへ変換する（例外の可能性あり）
					ObjectMapper mapper = new ObjectMapper();
					JsonNode node = mapper.readTree(json);
					
					
					//forecast(配列)をForEachで配列分繰り返す（例外の可能性あり）
					for(JsonNode shop:node.get("results").get("shop")) {
						//データクラスの生成（result1件分）
						ShopData shopdata = new ShopData();
						
						//idをDataクラスへ設定
						shopdata.setId(shop.get("id").asText());
						//nameをDataクラスへ設定
						shopdata.setName(shop.get("name").asText());
						//logo_imageをDataクラスへ設定
						shopdata.setLogo_image(shop.get("logo_image").asText());
						//name_kanaをDataクラスへ設定
						shopdata.setName_kana(shop.get("name_kana").asText());
						//addressをDataクラスへ設定
						shopdata.setAddress(shop.get("address").asText());
						//accessをDataクラスへ設定
						shopdata.setAccess(shop.get("access").asText());
						//urlをDataクラスへ設定
						shopdata.setUrl(shop.get("urls").get("pc").asText());
						//idをDataクラスへ設定
						shopdata.setImage(shop.get("photo").get("mobile").get("l").asText());
						
						//DataクラスをEneityの配列に追加
						shopEntity.getResults().add(shopdata);
					}
					
				}catch(IOException e) {
					//例外発生時は、エラーメッセージの詳細を標準エラー出力
					e.printStackTrace();
				}
				return shopEntity;
				
			}

		}