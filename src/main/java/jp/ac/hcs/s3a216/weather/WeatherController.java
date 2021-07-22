package jp.ac.hcs.s3a216.weather;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;
	
	/**
	 * 天気項目を検索し、結果画面を表示する。
	 * @param zipcode 検索する天気(ハイフンなし)
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面 - 天気
	 */
	
	@PostMapping("/weather")
	public String getWeather(Principal principal,Model model) {
		
		
		String cityCode="016010";
		WeatherEntity weatherEntity = weatherService.getWeather(cityCode);
		model.addAttribute("weatherEntity", weatherEntity);
		
		return "weather/weather";
	}

}
