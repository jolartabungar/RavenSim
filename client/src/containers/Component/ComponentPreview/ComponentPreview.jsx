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

const ComponentPreview = ({ componentReducer }) => {
  const {
    isHidden, x, y, type,
  } = componentReducer.sidebar;
  return (
    <Hideable isHidden={isHidden}>
      <ComponentDemux
        type={type}
        andGate={<AndGateShape x={x} y={y} />}
        threeInputAndGate={<ThreeInputAndGateShape x={x} y={y} />}
        clock={<ClockShape x={x} y={y} />}
        notGate={<NotGateShape x={x} y={y} />}
        xorGate={<XorGateShape x={x} y={y} />}
        orGate={<OrGateShape x={x} y={y} />}
        nandGate={<NandGateShape x={x} y={y} />}
        norGate={<NorGateShape x={x} y={y} />}
        xnorGate={<XnorGateShape x={x} y={y} />}
        dFlipFlop={<DFlipFlopShape x={x} y={y} />}
        tFlipFlop={<TFlipFlopShape x={x} y={y} />}
        jkFlipFlop={<JKFlipFlopShape x={x} y={y} />}
        rsFlipFlop={<RSFlipFlopShape x={x} y={y} />}
        source={<SourceShape x={x} y={y} />}
        inputButton={<InputButtonShape x={x} y={y} />}
      />
    </Hideable>
  );
};

const mapStateToProps = (state) => ({
  componentReducer: state.componentReducer,
});

export default connect(mapStateToProps)(ComponentPreview);
