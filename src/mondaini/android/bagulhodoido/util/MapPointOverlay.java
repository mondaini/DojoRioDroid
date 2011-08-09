package mondaini.android.bagulhodoido.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MapPointOverlay extends Overlay {
	private Paint paint = new Paint();
	private final int imgId;
	private final GeoPoint geoPoint;
	
	public MapPointOverlay(GeoPoint geoPoint, int imgId){
		this.geoPoint = geoPoint;
		this.imgId = imgId;
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);
		
		Point p = mapView.getProjection().toPixels(geoPoint, null);
		Bitmap bitmap = BitmapFactory.decodeResource(mapView.getResources(), imgId);
		RectF rectF = new RectF(p.x, p.y, p.x+bitmap.getWidth(), p.y+bitmap.getHeight());
		canvas.drawBitmap(bitmap, null, rectF, paint);
	}	
	
}
