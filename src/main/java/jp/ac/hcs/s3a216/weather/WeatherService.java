package jp.ac.hcs.s3a216.weather;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 天気予報操作をする
 * 天気予報APIを活用
 *-https://weather.tsukumijima.net/api
 */

@Transactional
@Service
public class WeatherService {
	
	@Autowired
	RestTemplate restTemplate;
	
	/**天気予報APIを活用　リクエストURL*/
	private static final String URL = "https://weather.tsukumijima.net/api/forecast?city={cityCode}";
	/**
	 * 指定した天気に紐づく天気予報情報を取得する。
	 * @param cityCode 都市コード
	 * @return WeatherEntity
	 */
	public WeatherEntity getWeather(String cityCode) {
		//APIにアクセスして、結果を取得
		String json = restTemplate.getForObject(URL, String.class,cityCode);
		
		//エンティティクラスを生成する
		WeatherEntity weatherEntity = new WeatherEntity();
		
		//jsonクラスへの変換失敗のため、例外処理
		try {
			//変換クラスを生成し、文字列からjsonクラスへ変換する（例外の可能性あり）
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			
			weatherEntity.setDescription(node.get("description").get("text").asText());
			
			//forecast(配列)をForEachで配列分繰り返す（例外の可能性あり）
			for(JsonNode forecast:node.get("forecasts")) {
				//データクラスの生成（result1件分）
				WeatherData data = new WeatherData();
				
				//dataLabelをDataクラスへ設定
				data.setDateLabel(forecast.get("dateLabel").asText());
				//telopをDataクラスへ設定
				data.setTelop(forecast.get("telop").asText());
				//DataクラスをEneityの配列に追加
				weatherEntity.getForecasts().add(data);
			}
			
		}catch(IOException e) {
			//例外発生時は、エラーメッセージの詳細を標準エラー出力
			e.printStackTrace();
		}
		return weatherEntity;
		
	}

}


