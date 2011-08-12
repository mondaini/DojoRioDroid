package mondaini.android.dojorio.db.json;

public class PlacemarkEntity {
	private String id;
	private PointEntity Point;
	
	public PointEntity getPoint() {
		return Point;
	}
	public void setPoint(PointEntity Point) {
		this.Point = Point;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
