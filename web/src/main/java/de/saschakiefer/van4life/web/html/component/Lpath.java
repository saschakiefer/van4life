package de.saschakiefer.van4life.web.html.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.xdev.vaadin.maps.leaflet.flow.data.LComponent;
import software.xdev.vaadin.maps.leaflet.flow.data.LPolygonOptions;

public class Lpath implements LComponent {
    private final LPolygonOptions properties;
    private final String geoJsonString;

    public Lpath(String geoJsonString) {
        this.properties = new LPolygonOptions();
        this.geoJsonString = geoJsonString.replace("MultiLineString", "LineString");
    }

    public boolean isStroke() {
        return this.properties.isStroke();
    }

    /**
     * Draws a border, default is true.
     *
     * @param stroke
     */
    public void setStroke(final boolean stroke) {
        this.properties.setStroke(stroke);
    }

    public String getStrokeColor() {
        return this.properties.getColor();
    }

    /**
     * Set a Color to the border.
     *
     * @param strokeColor
     */
    public void setStrokeColor(final String strokeColor) {
        this.properties.setColor(strokeColor);
    }

    public double getStrokeOpacity() {
        return this.properties.getOpacity();
    }

    /**
     * Sets the opacity of the border.
     *
     * @param strokeOpacity
     */
    public void setStrokeOpacity(final double strokeOpacity) {
        this.properties.setOpacity(strokeOpacity);
    }

    public int getStrokeWeight() {
        return this.properties.getWeight();
    }

    /**
     * Sets the width of the border.
     *
     * @param strokeWeight
     */
    public void setStrokeWeight(final int strokeWeight) {
        this.properties.setWeight(strokeWeight);
    }

    public String getLineJoin() {
        return this.properties.getLineJoin();
    }

    /**
     * A string that defines shape to be used at the corners of the stroke.<br>
     * <li>miter</li>
     * <li>round</li>
     * <li>bevel</li>
     * <li>miter-clip</li>
     * <li>arcs</li>
     *
     * @param lineJoin
     */
    public void setLineJoin(final String lineJoin) {
        this.properties.setLineJoin(lineJoin);
    }

    public String getDashArray() {
        return this.properties.getDashArray();
    }

    /**
     * A string that defines the stroke dash pattern.<br>
     * For example: "2 1 3 1 2"
     *
     * @param dashArray
     */
    public void setDashArray(final String dashArray) {
        this.properties.setDashArray(dashArray);
    }

    public String getDashOffset() {
        return this.properties.getDashOffset();
    }

    /**
     * A string that defines the distance into the dash pattern to start the dash.<br>
     * For example: "2" - The start of the dash array computation is pulled by 3 user units
     *
     * @param dashOffset
     */
    public void setDashOffset(final String dashOffset) {
        this.properties.setDashOffset(dashOffset);
    }

    @Override
    public String getPopup() {
        return this.properties.getPopup();
    }

    /**
     * Set Pop-up message.
     *
     * @param popup
     */
    public void setPopup(final String popup) {
        this.properties.setPopup(popup);
    }
    
    @Override
    public String buildClientJSItems() throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        // return "let item = L.polygon("
        //         + mapper.writeValueAsString(this.geometry.getCoordinates()) + ","
        //         + mapper.writeValueAsString(this.properties)
        //         + ");";
        return "let item = L.geoJson("
                + geoJsonString.replace("MultiLineString", "LineString") + ","
                + mapper.writeValueAsString(this.properties)
                + ");";
    }

}
