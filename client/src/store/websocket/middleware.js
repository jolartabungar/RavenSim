/* eslint-disable class-methods-use-this */
/* eslint-disable max-classes-per-file */
import { wsConnected, wsDisconnected } from './actions';
import { WS_CONNECT, WS_DISCONNECT } from './types';
import {
  TICK,
  COMMAND,
  EVENT,
  START_SIMULATION,
  SET_SIGNAL,
  TOGGLE_POKE,
  BUTTON_PRESS,
  LOAD_CIRCUIT,
  SAVE_CIRCUIT,
  CIRCUIT_MODEL,
} from '../command/types';
import { tick } from '../command/actions';
import { CREATE_WIRE } from '../wire/types';
import {
  CREATE_COMPONENT,
  AND_GATE,
  CLOCK,
  WIRE,
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
  HALF_ADDER,
  FULL_ADDER,
  HALF_SUBTRACTOR,
  FULL_SUBTRACTOR,
  SOURCE,
  INPUT_BUTTON,
} from '../component/types';
import {
  clockSize,
  cellSize,
  largeNotGateWidth,
  largeNotGateHeight,
  largeNegateRadius,
  largeAndGateWidth,
  largeXnorGateWidth,
  largeNorGateWidth,
  largeXorGateWidth,
  largeOrGateWidth,
  flipFlopSize,
  muxSize,
} from '../../util/style';
import { createPorts, clearPorts } from '../port/actions';
import { createWire, setWireSignal, clearWires } from '../wire/actions';
import { clearGrid, createComponent } from '../component/actions';

class MessageFactory {
  constructor(socket) {
    this.socket = socket;
  }

  send(fileName = '') {
    const message = this.buildMessage();
    if (fileName){
      message['fileName'] = fileName;
    }

    if (message !== null) {
      this.socket.send(JSON.stringify(message));
    }
  }

  buildMessage() {
    throw new Error('Expected an implementation from the subclass');
  }
}

class CreateComponentMessage extends MessageFactory {
  constructor(socket, type, id, points) {
    super(socket);
    this.type = type;
    this.id = id;
    this.ports = this.getPortLocations(points);
  }

  getPortLocations(points) {
    const [x1, y1, , , , , x4, y4] = points;
    const ports = [];
    switch (this.type) {
      case XOR_GATE:
        for (let i = 1; i <= 3; i++) {
          ports.push({
            x: i === 2 ? x1 + largeXorGateWidth : x1,
            y: y1 + cellSize * i,
          });
        }
        break;
      case OR_GATE:
        for (let i = 1; i <= 3; i++) {
          ports.push({
            x: i === 2 ? x1 + largeOrGateWidth : x1,
            y: y1 + cellSize * i,
          });
        }
        break;
      case NOR_GATE:
        for (let i = 1; i <= 3; i++) {
          ports.push({
            x: i === 2 ? x1 + largeNorGateWidth + largeNegateRadius * 2 : x1,
            y: y1 + cellSize * i,
          });
        }
        break;
      case XNOR_GATE:
        for (let i = 1; i <= 3; i++) {
          ports.push({
            x: i === 2 ? x1 + largeXnorGateWidth + largeNegateRadius * 2 : x1,
            y: y1 + cellSize * i,
          });
        }
        break;
      case NAND_GATE:
        for (let i = 1; i <= 3; i++) {
          ports.push({
            x: i === 2 ? x1 + largeAndGateWidth + largeNegateRadius * 2 : x1,
            y: y1 + cellSize * i,
          });
        }
        break;
      case AND_GATE:
        for (let i = 1; i <= 3; i++) {
          ports.push({ x: i === 2 ? x1 + largeAndGateWidth : x1, y: y1 + cellSize * i });
        }
        break;
      case CLOCK:
        ports.push({ x: x1 + clockSize, y: y1 + clockSize / 2 });
        break;
      case INPUT_BUTTON:
        ports.push({ x: x1 + clockSize, y: y1 + 0.5 * clockSize });
        break;
      case SOURCE:
        ports.push({ x: x1 + clockSize, y: y1 + 0.5 * clockSize });
        break;
      case WIRE:
        ports.push({ x: x1, y: y1 });
        ports.push({ x: x4, y: y4 });
        break;
      case NOT_GATE:
        ports.push({ x: x1, y: y1 + largeNotGateHeight / 2 });
        ports.push({
          x: x1 + largeNotGateWidth + largeNegateRadius * 2,
          y: y1 + largeNotGateHeight / 2,
        });
        break;
      case D_FLIP_FLOP:
        ports.push({ x: x1, y: y1 + cellSize });
        ports.push({ x: x1, y: y1 + 3 * cellSize });
        ports.push({ x: x1 + flipFlopSize, y: y1 + cellSize });
        ports.push({ x: x1 + flipFlopSize, y: y1 + 3 * cellSize });
        break;
      case T_FLIP_FLOP:
        ports.push({ x: x1, y: y1 + cellSize });
        ports.push({ x: x1, y: y1 + 3 * cellSize });
        for (let i = 1; i <= 3; i++) {
          ports.push({ x: x1 + cellSize * i, y: y1 + flipFlopSize });
        }
        ports.push({ x: x1 + flipFlopSize, y: y1 + cellSize });
        ports.push({ x: x1 + flipFlopSize, y: y1 + 3 * cellSize });
        break;
      case JK_FLIP_FLOP:
        for (let i = 1; i <= 3; i++) {
          ports.push({ x: x1, y: y1 + i * cellSize });
        }
        for (let i = 1; i <= 3; i++) {
          ports.push({ x: x1 + cellSize * i, y: y1 + flipFlopSize });
        }
        ports.push({ x: x1 + flipFlopSize, y: y1 + cellSize });
        ports.push({ x: x1 + flipFlopSize, y: y1 + 3 * cellSize });
        break;
      case RS_FLIP_FLOP:
        for (let i = 1; i <= 3; i++) {
          ports.push({ x: x1, y: y1 + i * cellSize });
        }
        for (let i = 1; i <= 3; i++) {
          ports.push({ x: x1 + cellSize * i, y: y1 + flipFlopSize });
        }
        ports.push({ x: x1 + flipFlopSize, y: y1 + cellSize });
        ports.push({ x: x1 + flipFlopSize, y: y1 + 3 * cellSize });
        break;
      case HALF_ADDER:
      case HALF_SUBTRACTOR:
        ports.push({ x: x1, y: y1 + cellSize });
        ports.push({ x: x1, y: y1 + 3 * cellSize });
        ports.push({ x: x1 + muxSize, y: y1 + cellSize });
        ports.push({ x: x1 + muxSize, y: y1 + 3 * cellSize });
        break;
      case FULL_ADDER:
      case FULL_SUBTRACTOR:
        ports.push({ x: x1, y: y1 + cellSize });
        ports.push({ x: x1, y: y1 + 2 * cellSize });
        ports.push({ x: x1, y: y1 + 3 * cellSize });
        ports.push({ x: x1 + muxSize, y: y1 + cellSize });
        ports.push({ x: x1 + muxSize, y: y1 + 3 * cellSize });
        break;
      default:
        throw new Error(`${this.type}: is an unimplemented type in the CreateComponentMessage`);
    }
    return ports;
  }

  buildMessage() {
    const { id, type, ports } = this;
    let properties;
    switch (type) {
      case XOR_GATE:
      case OR_GATE:
      case NAND_GATE:
      case NOR_GATE:
      case XNOR_GATE:
      case AND_GATE:
        properties = {
          inputs: [ports[0], ports[2]],
          outputs: [ports[1]],
        };
        break;
      case SOURCE:
      case INPUT_BUTTON:
      case CLOCK:
        properties = {
          output: ports[0],
        };
        break;
      case WIRE:
        properties = {
          from: ports[0],
          to: ports[1],
        };
        break;
      case NOT_GATE:
        properties = {
          inputs: [ports[0]],
          outputs: [ports[1]],
        };
        break;
      case D_FLIP_FLOP:
      case HALF_ADDER:
      case HALF_SUBTRACTOR:
      case T_FLIP_FLOP:
        properties = {
          inputs: [ports[0], ports[1]],
          outputs: [ports[2], ports[3]],
        };
        break;
      case JK_FLIP_FLOP:
      case RS_FLIP_FLOP:
        properties = {
          inputs: [ports[0], ports[1], ports[2], ports[3], ports[4], ports[5]],
          outputs: [ports[6], ports[7]],
        };
        break;
      case FULL_ADDER:
      case FULL_SUBTRACTOR:
        properties = {
          inputs: [ports[0], ports[1], ports[2]],
          outputs: [ports[3], ports[4]],
        };
        break;
      default:
        throw new Error(`${this.type}: is an unimplemented type in the CreateComponentMessage`);
    }
    return {
      type: EVENT,
      message: {
        circuitChanges: [
          {
            action: CREATE_COMPONENT,
            type,
            id,
            properties,
          },
        ],
      },
    };
  }
}

class StartSimulationEvent extends MessageFactory {
  buildMessage() {
    return { type: COMMAND, message: START_SIMULATION };
  }
}

class ButtonPressEvent extends MessageFactory {
  buildMessage() {
    return { type: COMMAND, message: BUTTON_PRESS, id: 69 };
  }
}

class LoadEvent extends MessageFactory {
  buildMessage() {
    return { type: COMMAND, message: LOAD_CIRCUIT };
  }
}

class SaveEvent extends MessageFactory {
  buildMessage() {
    return { type: COMMAND, message: SAVE_CIRCUIT };
  }
}

let id = 0;
const wsMiddleware = () => {
  let socket = null;
  let allowMessages = true;

  const onOpen = (store) => (event) => {
    store.dispatch(wsConnected(event.target.url));
  };

  const onClose = (store) => () => {
    store.dispatch(wsDisconnected());
  };

  const onMessage = (store) => (event) => {
    const { type, message } = JSON.parse(event.data);
    switch (type) {
      case COMMAND:
        switch (message) {
          case TICK:
            store.dispatch(tick());
            break;
          default:
            throw new Error(`${message}: is an invalid message`);
        }
        break;
      case EVENT: {
        const { circuitChanges } = message;
        if (circuitChanges === null || !Array.isArray(circuitChanges)) {
          throw new Error(`${message}: is an invalid message`);
        }
        circuitChanges.forEach((circuitChange) => {
          const {
            action,
            properties: { signal },
          } = circuitChange;
          switch (action) {
            case SET_SIGNAL: {
              store.dispatch(setWireSignal(parseInt(circuitChange.id, 10), signal));
              break;
            }
            default:
              throw new Error(`${action}: is an invalid action`);
          }
        });
        break;
      }
      case CIRCUIT_MODEL: {
        allowMessages = false;
        const circuitModel = message;
        store.dispatch(clearGrid());
        store.dispatch(clearPorts());
        store.dispatch(clearWires());
        for (let i = 0; i < circuitModel.length; i++) {
          const c = circuitModel[i];

          if (c.type === WIRE) {
            const difference = Math.round(
              Math.abs((c.start.x - c.end.x) / 2) / cellSize,
            ) * cellSize;
            const x2 = c.start.x < c.end.x ? c.start.x + difference : c.start.x - difference;
            const y2 = c.start.y;

            const x3 = x2;
            const y3 = c.end.y;
            const points = [c.start.x, c.start.y, x2, y2, x3, y3, c.end.x, c.end.y];
            store.dispatch(createWire(points));
          } else {
            const componentMessage = new CreateComponentMessage(
              socket, c.type, id++, [c.point.x, c.point.y],
            );
            store.dispatch(createComponent(c.type, c.point.x, c.point.y));
            store.dispatch(createPorts(componentMessage.ports));
          }
        }
        allowMessages = true;
        break;
      }
      default:
        throw new Error(`${type}: is an invalid type`);
    }
  };

  return (store) => (next) => (action) => {
    switch (action.type) {
      case WS_CONNECT:
        if (socket !== null) {
          socket.close();
        }
        // Connect to the remote host.
        socket = new WebSocket(action.host);
        // Attach the various WebSocket handlers.
        socket.onmessage = onMessage(store);
        socket.onclose = onClose(store);
        socket.onopen = onOpen(store);
        break;
      case WS_DISCONNECT:
        if (socket !== null) {
          socket.close();
        }
        socket = null;
        break;
      case CREATE_WIRE: {
        const { points } = action;
        action.id = id;
        new CreateComponentMessage(socket, WIRE, id++, points).send();
        break;
      }
      case CREATE_COMPONENT: {
        const { componentType, x, y } = action;
        if (componentType !== undefined && allowMessages) {
          const message = new CreateComponentMessage(socket, componentType, id++, [x, y]);
          store.dispatch(createPorts(message.ports));
          message.send();
        }
        break;
      }
      case COMMAND: {
        const { message } = action;
        switch (message) {
          case START_SIMULATION:
            new StartSimulationEvent(socket).send();
            break;
          case TICK:
            break;
          case TOGGLE_POKE:
            break;
          case BUTTON_PRESS:
            new ButtonPressEvent(socket).send();
            break;
          case SAVE_CIRCUIT:
            new SaveEvent(socket).send(action.fileName);
            break;
          case LOAD_CIRCUIT:
            new LoadEvent(socket).send(action.fileName);
            break;
          default:
            throw new Error(`${message}: is an invalid command message`);
        }
        break;
      }
      default:
        break;
    }
    return next(action);
  };
};

export default wsMiddleware();
