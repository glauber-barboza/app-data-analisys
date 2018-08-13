package com.glauber.appdataanalisys.fatory;

public class ItensVendaFactory {
    public static class Builder {
        StringBuilder buildVenda = new StringBuilder();
        private String id;
        private String quantidade;
        private String valor;
        private String delim;

        public Builder() {
            id = "1";
            quantidade="10";
            valor="100";
            delim = "-";
        }

        public Builder semId() {
            id = " ";
            return this;
        }

        public Builder semQuantidade() {
            quantidade = "  ";
            return this;
        }

        public Builder semValor() {
            valor = " ";
            return this;
        }

        public Builder semDelim() {
            delim = "";
            return this;
        }

        public String build() {
            buildVenda.append(id);
            buildVenda.append(delim);
            buildVenda.append(quantidade);
            buildVenda.append(delim);
            buildVenda.append(valor);
            return buildVenda.toString();
        }
    }
}
