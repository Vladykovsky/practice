package by.apexintegration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Akulenko Vladimir
 * @since 21.07.22
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DerivativeSignal {

    private String name;

    private List<Point> points;

    private DerivativeType type;


}
