import React from 'react';
import Collapsible from 'react-collapsible';
import { connect } from 'react-redux';
import MenuItemTitle from '../MenuItemTitle';
import { setComponentPreviewType, setComponentShadowType } from '../../../store/component/actions';
import { componentWidth } from '../../../util/style';
import {
  AND_GATE,
  CLOCK,
  NOT_GATE,
  XOR_GATE,
  OR_GATE,
  NAND_GATE,
  NOR_GATE,
  XNOR_GATE,
  D_FLIP_FLOP,
  T_FLIP_FLOP,
  JK_FLIP_FLOP,
  RS_FLIP_FLOP,
  SOURCE,
  INPUT_BUTTON,
  HALF_ADDER,
  FULL_ADDER,
  HALF_SUBTRACTOR,
  FULL_SUBTRACTOR,
  EIGHT_TO_THREE_ENCODER,
  THREE_TO_EIGHT_DECODER,
  TWO_TO_ONE_MUX,
  FOUR_TO_ONE_MUX,
  ONE_TO_TWO_DEMUX,
  ONE_TO_FOUR_DEMUX,
  JK_FLIP_FLOP_PRE_CLR,
} from '../../../store/component/types';
import AndGateShape from '../../Component/ComponentShape/LogicGate/AndGateShape';
import ClockShape from '../../Component/ComponentShape/Signal/ClockShape';
import NotGateShape from '../../Component/ComponentShape/LogicGate/NotGateShape';
import XorGateShape from '../../Component/ComponentShape/LogicGate/XorGateShape';
import OrGateShape from '../../Component/ComponentShape/LogicGate/OrGateShape';
import NandGateShape from '../../Component/ComponentShape/LogicGate/NandGateShape';
import NorGateShape from '../../Component/ComponentShape/LogicGate/NorGateShape';
import XnorGateShape from '../../Component/ComponentShape/LogicGate/XnorGateShape';
import DFlipFlopShape from '../../Component/ComponentShape/Memory/DFlipFlopShape';
import TFlipFlopShape from '../../Component/ComponentShape/Memory/TFlipFlopShape';
import JKFlipFlopShape from '../../Component/ComponentShape/Memory/JKFlipFlopShape';
import JKFlipFlopPRECLRShape from '../../Component/ComponentShape/Memory/JKFlipFlopPRECLRShape';
import RSFlipFlopShape from '../../Component/ComponentShape/Memory/RSFlipFlopShape';
import HalfAdderShape from '../../Component/ComponentShape/Arithmetic/HalfAdderShape';
import FullAdderShape from '../../Component/ComponentShape/Arithmetic/FullAdderShape';
import HalfSubtractorShape from '../../Component/ComponentShape/Arithmetic/HalfSubtractorShape';
import FullSubtractorShape from '../../Component/ComponentShape/Arithmetic/FullSubtractorShape';
import EighttoThreeEncoderShape from '../../Component/ComponentShape/Plexers/EighttoThreeEncoderShape';
import ThreetoEightDecoderShape from '../../Component/ComponentShape/Plexers/ThreetoEightDecoderShape';
import TwoToOneMuxShape from '../../Component/ComponentShape/Plexers/TwoToOneMuxShape';
import FourToOneMuxShape from '../../Component/ComponentShape/Plexers/FourToOneMuxShape';
import OneToTwoDemuxShape from '../../Component/ComponentShape/Plexers/OneToTwoDemuxShape';
import OneToFourDemuxShape from '../../Component/ComponentShape/Plexers/OneToFourDemuxShape';
import SourceShape from '../../Component/ComponentShape/Wiring/SourceShape';
import InputButtonShape from '../../Component/ComponentShape/Signal/InputButtonShape';

/**
 * Given a sidebar item, creats a new Component when a user begins clicking on an element.
 * @param {*} props a list of items in the sidebar.
 * @author:kyhorne
 */
const DropDownMenu = (props) => {
  const { title, children } = props;

  const onMouseDown = (instanceOf) => {
    let type;
    switch (instanceOf) {
      case AndGateShape:
        type = AND_GATE;
        break;
      case ClockShape:
        type = CLOCK;
        break;
      case SourceShape:
        type = SOURCE;
        break;
      case NotGateShape:
        type = NOT_GATE;
        break;
      case XorGateShape:
        type = XOR_GATE;
        break;
      case OrGateShape:
        type = OR_GATE;
        break;
      case NandGateShape:
        type = NAND_GATE;
        break;
      case NorGateShape:
        type = NOR_GATE;
        break;
      case XnorGateShape:
        type = XNOR_GATE;
        break;
      case DFlipFlopShape:
        type = D_FLIP_FLOP;
        break;
      case TFlipFlopShape:
        type = T_FLIP_FLOP;
        break;
      case JKFlipFlopShape:
        type = JK_FLIP_FLOP;
        break;
      case JKFlipFlopPRECLRShape:
        type = JK_FLIP_FLOP_PRE_CLR;
        break;
      case RSFlipFlopShape:
        type = RS_FLIP_FLOP;
        break;
      case InputButtonShape:
        type = INPUT_BUTTON;
        break;
      case HalfAdderShape:
        type = HALF_ADDER;
        break;
      case FullAdderShape:
        type = FULL_ADDER;
        break;
      case HalfSubtractorShape:
        type = HALF_SUBTRACTOR;
        break;
      case FullSubtractorShape:
        type = FULL_SUBTRACTOR;
        break;
      case EighttoThreeEncoderShape:
        type = EIGHT_TO_THREE_ENCODER;
        break;
      case ThreetoEightDecoderShape:
        type = THREE_TO_EIGHT_DECODER;
        break;
      case TwoToOneMuxShape:
        type = TWO_TO_ONE_MUX;
        break;
      case FourToOneMuxShape:
        type = FOUR_TO_ONE_MUX;
        break;
      case OneToTwoDemuxShape:
        type = ONE_TO_TWO_DEMUX;
        break;
      case OneToFourDemuxShape:
        type = ONE_TO_FOUR_DEMUX;
        break;
      default:
        throw new Error(`${type}: is an unimplemented type in the DropDownMenu`);
    }
    props.setComponentShadowType(type);
    props.setComponentPreviewType(type);
  };

  return (
    <div>
      <Collapsible
        triggerOpenedClassName="down"
        triggerClassName="right"
        open
        trigger={<MenuItemTitle title={title} />}
      >
        <div style={{ display: 'flex', flexWrap: 'wrap' }}>
          {React.Children.map(children, (child) => (
            // eslint-disable-next-line jsx-a11y/no-static-element-interactions
            <div
              style={{ marginBottom: '20px', width: `${componentWidth}%` }}
              onMouseDown={() => onMouseDown(child.props.type)}
              onTouchStart={() => onMouseDown(child.props.type)}
            >
              {child}
            </div>
          ))}
        </div>
      </Collapsible>
    </div>
  );
};

const mapDispatchToProps = {
  setComponentPreviewType,
  setComponentShadowType,
};

export default connect(null, mapDispatchToProps)(DropDownMenu);
