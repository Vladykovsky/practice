package by.apexintegration.service;

import by.apexintegration.exceptions.CalculationException;
import by.apexintegration.model.DerivativeSignal;
import by.apexintegration.model.Signal;

public interface IDerivativeService {

    DerivativeSignal calculatedFirstDerivative(Signal signal) throws CalculationException;

    DerivativeSignal calculateSecondDerivative(Signal signal) throws CalculationException;

}
