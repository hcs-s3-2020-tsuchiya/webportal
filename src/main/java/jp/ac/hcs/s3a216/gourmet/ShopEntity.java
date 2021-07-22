package jp.ac.hcs.s3a216.gourmet;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * グルメ検索機能の検索情報
 * 各項目のデータについて、APIを活用する
 *
 */
@Data
public class ShopEntity {
	/**ステータス*/
	private String shopname;
	

	/**グルメ情報のリスト*/
	private List<ShopData> results = new ArrayList<ShopData>();


}
