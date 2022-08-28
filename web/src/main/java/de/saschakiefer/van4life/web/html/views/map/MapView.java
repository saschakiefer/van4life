package de.saschakiefer.van4life.web.html.views.map;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.saschakiefer.van4life.application.service.CampsiteService;
import de.saschakiefer.van4life.web.html.views.MainLayout;
import software.xdev.vaadin.maps.leaflet.flow.LMap;
import software.xdev.vaadin.maps.leaflet.flow.data.LComponent;
import software.xdev.vaadin.maps.leaflet.flow.data.LMarker;
import software.xdev.vaadin.maps.leaflet.flow.data.LTileLayer;

@Component
@Scope("prototype")
@Route(value = "", layout = MainLayout.class)
@PageTitle("Campsites")
public class MapView extends VerticalLayout {
	public static final LTileLayer SATELLITE_TILE = new LTileLayer(
			"https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}",
			"Tiles &copy; Esri &mdash; Source: Esri, i-cubed, USDA, USGS, AEX, GeoEye, Getmapping, Aerogrid, IGN, IGP, UPR-EGP, and the GIS User Community",
			18
	);

	final CampsiteService campsiteService;
	List<LComponent> campsites;
	private LMap map;

	public MapView(CampsiteService campsiteService) {
		this.campsiteService = campsiteService;

		initMapComponents();
		add(map);
		setSizeFull();

		updateMap();
	}

	private void initMapComponents() {
		map = new LMap(51.163361, 10.447683, 6);
//		map.setTileLayer(SATELLITE_TILE);
		map.setTileLayer(LTileLayer.DEFAULT_OPENSTREETMAP_TILE);

		map.setSizeFull();
	}

	private void updateMap() {
		campsites = campsiteService.findAllCampsites().stream().map(campsite -> {
			LMarker marker = new LMarker(campsite.getPosition().getLatitude(), campsite.getPosition().getLongitude());
			marker.setPopup(campsite.getName());
			return marker;
		}).collect(Collectors.toList());

		map.addLComponents(campsites);
	}
}
