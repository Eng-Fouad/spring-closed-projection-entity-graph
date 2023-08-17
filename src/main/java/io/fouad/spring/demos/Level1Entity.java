package io.fouad.spring.demos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.Table;

@Entity
@Table(name = "level1")
@NamedEntityGraphs({
    @NamedEntityGraph(name = "Level1",
        attributeNodes = {
            @NamedAttributeNode(value = "level2", subgraph = "Level2"),
        }, subgraphs = {
        @NamedSubgraph(name = "Level2",
            attributeNodes = {
                @NamedAttributeNode(value = "level3")
            }
        )
    })
})
public class Level1Entity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Integer id;
    
    @Column(name = "name")
    String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level2_id")
    Level2Entity level2;
    
    public String getName(){return name;}
    public Level2Entity getLevel2(){return level2;}
}