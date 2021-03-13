import React from 'react';
import { connect } from 'react-redux';
import AndGateShape from '../ComponentShape/LogicGate/AndGateShape';
import ThreeInputAndGateShape from '../ComponentShape/LogicGate/ThreeInputAndGateShape';
import ClockShape from '../ComponentShape/Signal/ClockShape';
import Hideable from '../../Hideable';
import ComponentDemux from '../ComponentDemux';
import NotGateShape from '../ComponentShape/LogicGate/NotGateShape';
import XorGateShape from '../ComponentShape/LogicGate/XorGateShape';
import OrGateShape from '../ComponentShape/LogicGate/OrGateShape';
import NandGateShape from '../ComponentShape/LogicGate/NandGateShape';
import NorGateShape from '../ComponentShape/LogicGate/NorGateShape';
import XnorGateShape from '../ComponentShape/LogicGate/XnorGateShape';
import DFlipFlopShape from '../ComponentShape/Memory/DFlipFlopShape';
import TFlipFlopShape from '../ComponentShape/Memory/TFlipFlopShape';
import JKFlipFlopShape from '../ComponentShape/Memory/JKFlipFlopShape';
import RSFlipFlopShape from '../ComponentShape/Memory/RSFlipFlopShape';
import SourceShape from '../ComponentShape/Wiring/SourceShape';
import InputButtonShape from '../ComponentShape/Signal/InputButtonShape';

/**
 * A factory template for all component shapes to show where the component will snap to on the grid.
 * @param {*} componentReducer returns the type of the component passed through.
 * @author: kyhorne, kajhemmingsen
 */
const ComponentShadowFactory = ({ componentReducer }) => {
  const {
    isHidden, x, y, type,
  } = componentReducer.shadow;
  return (
    <Hideable isHidden={isHidden}>
      <ComponentDemux
        type={type}
        andGate={<AndGateShape x={x} y={y} isShadow />}
        threeInputAndGate={<ThreeInputAndGateShape x={x} y={y} isShadow />}
        clock={<ClockShape x={x} y={y} isShadow />}
        notGate={<NotGateShape x={x} y={y} isShadow />}
        xorGate={<XorGateShape x={x} y={y} isShadow />}
        orGate={<OrGateShape x={x} y={y} isShadow />}
        nandGate={<NandGateShape x={x} y={y} isShadow />}
        norGate={<NorGateShape x={x} y={y} isShadow />}
        xnorGate={<XnorGateShape x={x} y={y} isShadow />}
        dFlipFlop={<DFlipFlopShape x={x} y={y} isShadow />}
        tFlipFlop={<TFlipFlopShape x={x} y={y} isShadow />}
        jkFlipFlop={<JKFlipFlopShape x={x} y={y} isShadow />}
        rsFlipFlop={<RSFlipFlopShape x={x} y={y} isShadow />}
        source={<SourceShape x={x} y={y} isShadow />}
        inputButton={<InputButtonShape x={x} y={y} isShadow />}
      />
    </Hideable>
  );
};

const mapStateToProps = (state) => ({
  componentReducer: state.componentReducer,
});

export default connect(mapStateToProps)(ComponentShadowFactory);
