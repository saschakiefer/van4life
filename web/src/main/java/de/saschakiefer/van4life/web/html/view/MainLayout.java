package de.saschakiefer.van4life.web.html.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import de.saschakiefer.van4life.web.html.view.list.CampsiteListView;
import de.saschakiefer.van4life.web.html.view.map.MapView;

public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H3 logo = new H3("Van4Life");
        logo.addClassNames("text-l", "m-m");

        Button logout = new Button("Log out", e -> {
            return;
        });

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);

    }

    private void createDrawer() {
        RouterLink campsitesLink = new RouterLink("List", CampsiteListView.class);
        campsitesLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink mapLink = new RouterLink("Map", MapView.class);
        mapLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                mapLink,
                campsitesLink
//				new RouterLink("Dashboard", DashboardView.class)
        ));
    }
}
