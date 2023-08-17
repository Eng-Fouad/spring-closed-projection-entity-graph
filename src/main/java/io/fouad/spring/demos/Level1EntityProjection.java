package io.fouad.spring.demos;

public interface Level1EntityProjection {
    Integer getId();
    String getName();
    Level2EntityProjection getLevel2();
}