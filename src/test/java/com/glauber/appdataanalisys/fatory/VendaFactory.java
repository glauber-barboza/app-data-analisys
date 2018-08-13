package com.glauber.appdataanalisys.fatory;


public class VendaFactory {
    public static class Builder {
        StringBuilder buildVenda = new StringBuilder();
        StringBuilder itens = new StringBuilder();
        private String id;
        private String idVenda;
        private String itensVenda;
        private String vendedor;
        private String delim;

        public Builder() {
            id = "001";
            idVenda ="1";
            vendedor="Diego";
            delim = "ç";
            itens.append("[");
            itens.append(new ItensVendaFactory.Builder().build());
            itens.append("]");
            itensVenda = itens.toString();
        }

        public Builder semId() {
            id = "";
            return this;
        }

        public Builder semVendedor() {
            vendedor = " ";
            return this;
        }

        public Builder semIdVenda() {
            idVenda = " ";
            return this;
        }

        public Builder semItensVenda() {
            itensVenda = " ";
            return this;
        }

        public Builder semDelim() {
            delim = "";
            return this;
        }

        public String build() {
            buildVenda.append(id);
            buildVenda.append(delim);
            buildVenda.append(idVenda);
            buildVenda.append(delim);
            buildVenda.append(itensVenda);
            buildVenda.append(delim);
            buildVenda.append(vendedor);
            return buildVenda.toString();
        }
    }
}
