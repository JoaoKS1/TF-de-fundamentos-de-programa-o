public class Peca {
    /**
     * Representa uma peça do jogo de damas.
     */
        private boolean isDama;
        private int color;
        public Peca(int color) {
            this.color = color;
            isDama = false;
        }

        public boolean isDama() {
            return isDama;
        }

        public int getColor() {
            return color;
        }

        public void setDama() {
            isDama = true;
        }

        public void setDama(boolean isDama) {
            this.isDama = isDama;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }
