package jp.ac.hcs.s3a216.gourmet;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
public class GourmetController {
	@Autowired
	private GourmetService gourmetService;
	
	/**
	 * 項目を検索し、結果画面を表示する。
	 * @param zipcode 検索するグルメ情報(ハイフン)
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面 - グルメ
	 */
	
	@PostMapping("/gourmet")
	public String getWeather(@RequestParam(name="shopname") String shopname,Principal principal,Model model) {
		
		String large_service_area="SS40";
		ShopEntity shopEntity = gourmetService.getShops(shopname,large_service_area);
		model.addAttribute("shopEntity", shopEntity);
		
		return "gourmet/gourmet";
	}

}
