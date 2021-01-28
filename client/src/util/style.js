/**
 * Styling options go here, any colours or dimensions should go under their
 * corresponding type.
 * @author:kyhorne, kajhemmingsen
 */
// Colors
const lightPurple = '#bb86fc';
const darkPurple = '#3700b3';
const lightGray = '#e0e0e0';
const darkGray = '#303030';
const reallyDarkGray = '#121212';
const black = '#000';
const lightGreen = '#14cc14';
const darkGreen = '#036d19';
const pink = '#cf6679';

// Grid
export const cellSize = 20;
export const gridLineColor = darkGray;
export const gridWidth = window.innerWidth;
export const gridHeight = window.innerHeight;
export const gridBackgroundColor = reallyDarkGray;

// Shadow
export const shadowFillColor = lightPurple;
export const shadowStrokeColor = darkPurple;

// Wire
export const wireWidth = 3;

// Negate
export const smallNegateRadius = 5;
export const largeNegateRadius = cellSize / 2;

// Sidebar Components
export const stageSize = 50;

// Logic Gate
export const logicGateFillColor = darkGray;
export const logicGateStrokeColor = lightGray;
export const smallLogicGateStrokeWidth = 1;
export const logicGateStrokeWidth = 1;

// And Gate
export const largeAndGateWidth = cellSize * 4;
export const largeAndGateHeight = cellSize * 4;
export const smallAndGateWidth = stageSize;
export const smallAndGateHeight = stageSize;

// Nand Gate
export const largeNandGateWidth = largeAndGateWidth;
export const largeNandGateHeight = largeAndGateHeight;
export const smallNandGateWidth = stageSize - smallNegateRadius * 2;
export const smallNandGateHeight = stageSize;

// Not Gate
export const largeNotGateWidth = cellSize * 3;
export const largeNotGateHeight = cellSize * 2;
export const smallNotGateWidth = stageSize - smallNegateRadius * 2;
export const smallNotGateHeight = stageSize;

// Or Gate
export const largeOrGateWidth = cellSize * 4;
export const largeOrGateHeight = cellSize * 4;
export const smallOrGateWidth = stageSize;
export const smallOrGateHeight = stageSize;

// Nor Gate
export const largeNorGateWidth = largeOrGateWidth;
export const largeNorGateHeight = largeOrGateHeight;
export const smallNorGateWidth = stageSize - smallNegateRadius * 2;
export const smallNorGateHeight = stageSize;

// Xor Gate
export const largeXorGateWidth = cellSize * 4;
export const largeXorGateHeight = cellSize * 4;
export const smallXorGateWidth = stageSize;
export const smallXorGateHeight = stageSize;

// Xnor Gate
export const largeXnorGateWidth = cellSize * 4;
export const largeXnorGateHeight = cellSize * 4;
export const smallXnorGateWidth = stageSize - smallNegateRadius * 2;
export const smallXnorGateHeight = stageSize;

// Multiplexers and Arithmetics
export const muxSize = cellSize * 6;
export const smallMuxSize = stageSize;

// Flip-Flops
export const flipFlopSize = cellSize * 4;
export const smallFlipFlopSize = stageSize;

// Clock
export const clockSize = cellSize * 2;
export const clockSignalStrokeWidth = 7;
export const sidebarClockSignalStrokeWidth = 9;
export const sidebarClockSize = 50;

// LED
export const LEDRadius = cellSize;
export const sidebarLEDRadius = 25;
// Port
export const portRadius = 8;
export const portStrokeColor = pink;
export const portFillColor = lightGray;
export const portStrokeWidth = 2;

// Signal
export const highSignalColor = lightGreen;
export const lowSignalColor = darkGreen;

// Sidebar
export const sideBarFillColor = reallyDarkGray;
const componentsPerRow = 3;
export const componentWidth = 100 / componentsPerRow;

// Text
export const textColor = lightGray;

// Line break
export const lineBreakColor = black;
export const lineBreakWidth = 0.5;

// Header
export const headerBackgroundColor = black;
export const headerHeight = 6;

// Button
export const buttonFillColor = reallyDarkGray;
export const buttonWidth = 150;
export const buttonHeight = 30;

export const commonShadowProps = (isShadow, isSmall) => ({
  opacity: isShadow ? 0.6 : 1,
  dash: isShadow ? [20, 2] : undefined,
  fill: isShadow ? shadowFillColor : logicGateFillColor,
  stroke: isShadow ? shadowStrokeColor : logicGateStrokeColor,
  strokeWidth: isSmall ? 0.5 : isShadow ? 3 : logicGateStrokeWidth,
  shadowColor: isShadow ? undefined : '#000',
  shadowBlur: isShadow ? undefined : 2,
  shadowOffset: isShadow ? undefined : { x: 1, y: 1 },
  shadowOpacity: isShadow ? undefined : 0.4,
});

export const commonShapeProps = (x, y, draggable, isSmall) => ({
  onMouseEnter: isSmall
    ? () => {
      document.body.style.cursor = 'grab';
    }
    : undefined,
  onMouseLeave: () => {
    document.body.style.cursor = 'default';
  },
  x: isSmall ? 0.5 : x,
  y: isSmall ? 0.5 : y,
  draggable,
});
