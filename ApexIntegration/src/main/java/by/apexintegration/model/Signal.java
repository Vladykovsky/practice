package by.apexintegration.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Signal {

    private String name;

    private List<Point> points;

    private String description;

}
