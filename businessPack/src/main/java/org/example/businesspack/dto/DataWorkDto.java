package org.example.businesspack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;

import org.example.businesspack.entities.DataWork;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

@Builder
@AllArgsConstructor
public class DataWorkDto {

    private SimpleLongProperty id;
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

        count.addListener((obs, oldVal, newVal) -> updateTotal());
        price.addListener((obs, oldVal, newVal) -> updateTotal());
        vat.addListener((obs, oldVal, newVal) -> updateTotal());
        updateTotal();
    }

    public static DataWorkDto of(DataWork account) {
        return DataWorkDto.builder()
                .id(new SimpleLongProperty(account.getId()))
                .count(new SimpleIntegerProperty(account.getCount()))
                .group(new SimpleStringProperty(account.getGroup()))
                .summa(new SimpleObjectProperty<>(account.getSumma()))
                .vat(new SimpleObjectProperty<>(account.getVat()))
                .price(new SimpleObjectProperty<>(account.getPrice()))
                .unitMeas(new SimpleStringProperty(account.getUnitMeas()))
                .name(new SimpleStringProperty(account.getName()))
                .tab(account.getTab())
                .build();
    }

    public static DataWork to(DataWorkDto dataWorkDto) {
        return DataWork.builder()
                .id(dataWorkDto.getId())
                .unitMeas(dataWorkDto.getUnitMeas())
                .group(dataWorkDto.getGroup())
                .count(dataWorkDto.getCount())
                .price(dataWorkDto.getPrice())
                .name(dataWorkDto.getName())
                .vat(dataWorkDto.getVat())
                .summa(dataWorkDto.getSumma())
                .tab(dataWorkDto.getTab())
                .build();
    }

    public Long getId() {
        return id == null ? null : id.get();
    }

    public void setId(Long id) {
        if (this.id == null) this.id = new SimpleLongProperty();
        this.id.set(id);
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public String getGroup() {
        return group.get();
    }

    public void setGroup(String group) {
        this.group.set(group);
    }
    
    public SimpleStringProperty groupProperty() {
        return group;
    }

    public Integer getCount() {
        return count.get();
    }
    
    public void setCount(Integer count) {
        this.count.set(count);
    }

    public SimpleIntegerProperty countProperty() {
        return count;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public BigDecimal getPrice() {
        return price.get();
    }

    public void setPrice(BigDecimal price) {
        this.price.set(price);
    }

    public SimpleObjectProperty<BigDecimal> priceProperty() {
        return price;
    }

    public BigDecimal getSumma() {
        return summa.get();
    }

    public void setSumma(BigDecimal summa) {
        this.summa.set(summa);
    }

    public SimpleObjectProperty<BigDecimal> summaProperty() {
        return summa;
    }

    public String getUnitMeas() {
        return unitMeas.get();
    }

    public void setUnitMeas(String unitMeas) {
        this.unitMeas.set(unitMeas);
    }

    public SimpleStringProperty unitMeasProperty() {
        return unitMeas;
    }

    public BigDecimal getVat() {
        return vat.get();
    }

    public void setVat(BigDecimal vat) {
        this.vat.set(vat);
    }

    public SimpleObjectProperty<BigDecimal> vatProperty() {
        return vat;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    private void updateTotal() {
        BigDecimal ostSumma = BigDecimal.ONE.subtract(vat.get());
        summa.set(price.get().multiply(ostSumma.multiply(BigDecimal.valueOf(count.get()))));
    }    


}
