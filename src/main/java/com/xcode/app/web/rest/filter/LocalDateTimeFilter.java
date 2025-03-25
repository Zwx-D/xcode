package com.xcode.app.web.rest.filter;

import io.github.jhipster.service.filter.RangeFilter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class LocalDateTimeFilter extends RangeFilter<LocalDateTime> implements Serializable {

    public LocalDateTimeFilter() {
    }

    public LocalDateTimeFilter(LocalDateTimeFilter localDateTimeFilter) {
        super(localDateTimeFilter);
    }

    public LocalDateTimeFilter copy() {
        return new LocalDateTimeFilter(this);
    }

    @Override
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDateTimeFilter setEquals(LocalDateTime equals) {
        super.setEquals(equals);
        return this;
    }

    @Override
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDateTimeFilter setNotEquals(LocalDateTime equals) {
        super.setNotEquals(equals);
        return this;
    }

    @Override
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDateTimeFilter setGreaterThan(LocalDateTime equals) {
        super.setGreaterThan(equals);
        return this;
    }

    @Override
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDateTimeFilter setGreaterThanOrEqual(LocalDateTime equals) {
        super.setGreaterThanOrEqual(equals);
        return this;
    }

    @Override
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Deprecated
    public LocalDateTimeFilter setGreaterOrEqualThan(LocalDateTime equals) {
        super.setGreaterOrEqualThan(equals);
        return this;
    }

    @Override
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDateTimeFilter setLessThan(LocalDateTime equals) {
        super.setLessThan(equals);
        return this;
    }

    @Override
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDateTimeFilter setLessThanOrEqual(LocalDateTime equals) {
        super.setLessThanOrEqual(equals);
        return this;
    }

    @Override
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Deprecated
    public LocalDateTimeFilter setLessOrEqualThan(LocalDateTime equals) {
        super.setLessOrEqualThan(equals);
        return this;
    }

    @Override
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDateTimeFilter setIn(List<LocalDateTime> in) {
        super.setIn(in);
        return this;
    }

}
