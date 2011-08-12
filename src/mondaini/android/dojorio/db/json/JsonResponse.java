package mondaini.android.dojorio.db.json;

import java.util.List;


public class JsonResponse {
	private List<PlacemarkEntity> Placemark;

	public List<PlacemarkEntity> getPlacemark() {
		return Placemark;
	}

	public void setPlacemark(List<PlacemarkEntity> Placemark) {
		this.Placemark = Placemark;
	}
}