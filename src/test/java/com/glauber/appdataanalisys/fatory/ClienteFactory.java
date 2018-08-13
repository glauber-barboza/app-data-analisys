package com.glauber.appdataanalisys.fatory;

public class ClienteFactory {

    public static class Builder {
        StringBuilder buildCliente = new StringBuilder();
        private String id;
        private String cnpj;
        private String nome;
        private String businesArea;
        private String delim;

        public Builder() {
            id = "002";
            cnpj ="2345675434544345";
            nome="Jose da Silva";
            businesArea ="Rural";
            delim = "ç";
        }

        public Builder semId() {
            id = "";
            return this;
        }

        public Builder cnpjInvalido() {
            cnpj = "   ";
            return this;
        }

        public Builder nomeInvalido() {
            nome = "   ";
            return this;
        }

        public Builder businesAreaInvalido() {
            businesArea = "   ";
            return this;
        }

        public Builder semDelim() {
            delim = "";
            return this;
        }

        public String build() {
            buildCliente.append(id);
            buildCliente.append(delim);
            buildCliente.append(cnpj);
            buildCliente.append(delim);
            buildCliente.append(nome);
            buildCliente.append(delim);
            buildCliente.append(businesArea);
            return buildCliente.toString();
        }
    }
}
