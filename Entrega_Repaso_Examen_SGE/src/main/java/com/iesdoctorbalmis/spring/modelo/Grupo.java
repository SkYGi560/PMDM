package com.iesdoctorbalmis.spring.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String grupo;
    private String ciclo;
    private String tutor;
    public Grupo() {
    }
    public Grupo(String grupo, String ciclo, String tutor) {
        this.grupo = grupo;
        this.ciclo = ciclo;
        this.tutor = tutor;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getGrupo() {
        return grupo;
    }
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    public String getCiclo() {
        return ciclo;
    }
    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }
    public String getTutor() {
        return tutor;
    }
    public void setTutor(String tutor) {
        this.tutor = tutor;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
        result = prime * result + ((ciclo == null) ? 0 : ciclo.hashCode());
        result = prime * result + ((tutor == null) ? 0 : tutor.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Grupo other = (Grupo) obj;
        if (id != other.id)
            return false;
        if (grupo == null) {
            if (other.grupo != null)
                return false;
        } else if (!grupo.equals(other.grupo))
            return false;
        if (ciclo == null) {
            if (other.ciclo != null)
                return false;
        } else if (!ciclo.equals(other.ciclo))
            return false;
        if (tutor == null) {
            if (other.tutor != null)
                return false;
        } else if (!tutor.equals(other.tutor))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "GrupoServicio [id=" + id + ", grupo=" + grupo + ", ciclo=" + ciclo + ", tutor=" + tutor + "]";
    }

    

}
