package org.example.businesspack.dto;

import java.math.BigDecimal;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
public class DataWorkDto {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty group;
    private SimpleIntegerProperty count;
    private SimpleStringProperty name;
    private SimpleObjectProperty<BigDecimal> price;
    private SimpleObjectProperty<BigDecimal> summa;
    private SimpleStringProperty unitMeas;
    private SimpleObjectProperty<BigDecimal> vat;
    private String tab;

    public DataWorkDto(String tab) {
        group = new SimpleStringProperty("Не задано");
        count = new SimpleIntegerProperty(0);
        name = new SimpleStringProperty("Не задано");
        price = new SimpleObjectProperty<>(BigDecimal.ZERO);
        unitMeas = new SimpleStringProperty("Не задано");
        vat = new SimpleObjectProperty<>(BigDecimal.ZERO);
        summa = new SimpleObjectProperty<>();
        this.tab = tab;

        addProperty();
    }

    public void addProperty() {
        count.addListener((obs, oldVal, newVal) -> updateTotal());
        price.addListener((obs, oldVal, newVal) -> updateTotal());
        vat.addListener((obs, oldVal, newVal) -> updateTotal());
        updateTotal();
    }

    public Integer getIdParameter() {
        return id == null ? null : id.get();
    }

    public void setIdParameter(Integer id) {
        if (this.id == null) this.id = new SimpleIntegerProperty();
        this.id.set(id);
    }

    public String getGroupParameter() {
        return group.get();
    }

    public void setGroupParameter(String group) {
        this.group.set(group);
    }

    public Integer getCountParameter() {
        return count.get();
    }
    
    public void setCountParameter(Integer count) {
        this.count.set(count);
    }

    public String getNameParameter() {
        return name.get();
    }

    public void setNameParameter(String name) {
        this.name.set(name);
    }

    public BigDecimal getPriceParameter() {
        return price.get();
    }

    public void setPriceParameter(BigDecimal price) {
        this.price.set(price);
    }

    public BigDecimal getSummaParameter() {
        return summa.get();
    }

    public void setSummaParameter(BigDecimal summa) {
        this.summa.set(summa);
    }

    public String getUnitMeasParameter() {
        return unitMeas.get();
    }

    public void setUnitMeasParameter(String unitMeas) {
        this.unitMeas.set(unitMeas);
    }

    public BigDecimal getVatParameter() {
        return vat.get();
    }

    public void setVatParameter(BigDecimal vat) {
        this.vat.set(vat);
    }

    private void updateTotal() {
        BigDecimal ostSumma = BigDecimal.ONE.subtract(vat.get());
        summa.set(price.get().multiply(ostSumma.multiply(BigDecimal.valueOf(count.get()))));
    }    
}
