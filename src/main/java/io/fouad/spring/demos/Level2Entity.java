package io.fouad.spring.demos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "level2")
public class Level2Entity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Integer id;
    
    @Column(name = "name")
    String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level3_id")
    Level3Entity level3;
    
    public String getName(){return name;}
    public Level3Entity getLevel3(){return level3;}
}