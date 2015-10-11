package ensat.unimelb.ensat_mobile_app.data;

import java.util.Date;

/**
 * Created by purat on 8/10/2015.
 */
public class BioMaterialTransferDetail {
    private int ensat_id;
    private String bio_id;
    private String biomaterial_date;
    private String material;
    private int aliquot_sequence_id;
    private String freezer_number;
    private String freezershelf_number;
    private String rack_number;
    private String shelf_number;
    private String box_number;
    private String position_number;
    private String center_id;
    private String destination_center_id;
    private String transfered_date;
    private String status;
    private int acc_biomaterial_transfer_group_id;
    private int acc_biomaterial_transfer_id;


    public BioMaterialTransferDetail() {
    }

    public BioMaterialTransferDetail(int ensat_id, String bio_id, String biomaterial_date, String material, int aliquot_sequence_id, String freezer_number, String freezershelf_number, String rack_number, String shelf_number, String box_number, String position_number, String center_id, String destination_center_id, String transfered_date, String status, int transfer_group_id, int acc_biomaterial_transfer_id) {
        this.ensat_id = ensat_id;
        this.bio_id = bio_id;
        this.biomaterial_date = biomaterial_date;
        this.material = material;
        this.aliquot_sequence_id = aliquot_sequence_id;
        this.freezer_number = freezer_number;
        this.freezershelf_number = freezershelf_number;
        this.rack_number = rack_number;
        this.shelf_number = shelf_number;
        this.box_number = box_number;
        this.position_number = position_number;
        this.center_id = center_id;
        this.destination_center_id = destination_center_id;
        this.transfered_date = transfered_date;
        this.status = status;
        this.acc_biomaterial_transfer_group_id = transfer_group_id;
        this.acc_biomaterial_transfer_id = acc_biomaterial_transfer_id;
    }

    public BioMaterialTransferDetail(int ensat_id, String bio_id, String biomaterial_date, String material, int aliquot_sequence_id, String freezer_number, String freezershelf_number, String rack_number, String shelf_number, String box_number, String position_number, String center_id) {
        this.ensat_id = ensat_id;
        this.bio_id = bio_id;
        this.biomaterial_date = biomaterial_date;
        this.material = material;
        this.aliquot_sequence_id = aliquot_sequence_id;
        this.freezer_number = freezer_number;
        this.freezershelf_number = freezershelf_number;
        this.rack_number = rack_number;
        this.shelf_number = shelf_number;
        this.box_number = box_number;
        this.position_number = position_number;
        this.center_id = center_id;
    }

    public BioMaterialTransferDetail(String biomaterial_date) {
        this.biomaterial_date = biomaterial_date;
    }

    public BioMaterialTransferDetail(int ensat_id, String bio_id, String biomaterial_date) {
        this.ensat_id = ensat_id;
        this.bio_id = bio_id;
        this.biomaterial_date = biomaterial_date;
    }

    public int getEnsat_id() {
        return ensat_id;
    }

    public void setEnsat_id(int ensat_id) {
        this.ensat_id = ensat_id;
    }

    public String getBio_id() {
        return bio_id;
    }

    public void setBio_id(String bio_id) {
        this.bio_id = bio_id;
    }

    public String getBiomaterial_date() {
        return biomaterial_date;
    }

    public void setBiomaterial_date(String biomaterial_date) {
        this.biomaterial_date = biomaterial_date;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getAliquot_sequence_id() {
        return aliquot_sequence_id;
    }

    public void setAliquot_sequence_id(int aliquot_sequence_id) {
        this.aliquot_sequence_id = aliquot_sequence_id;
    }

    public String getFreezer_number() {
        return freezer_number;
    }

    public void setFreezer_number(String freezer_number) {
        this.freezer_number = freezer_number;
    }

    public String getFreezershelf_number() {
        return freezershelf_number;
    }

    public void setFreezershelf_number(String freezershelf_number) {
        this.freezershelf_number = freezershelf_number;
    }

    public String getRack_number() {
        return rack_number;
    }

    public void setRack_number(String rack_number) {
        this.rack_number = rack_number;
    }

    public String getShelf_number() {
        return shelf_number;
    }

    public void setShelf_number(String shelf_number) {
        this.shelf_number = shelf_number;
    }

    public String getBox_number() {
        return box_number;
    }

    public void setBox_number(String box_number) {
        this.box_number = box_number;
    }

    public String getPosition_number() {
        return position_number;
    }

    public void setPosition_number(String position_number) {
        this.position_number = position_number;
    }

    public String getCenter_id() {
        return center_id;
    }

    public void setCenter_id(String center_id) {
        this.center_id = center_id;
    }

    public String getDestination_center_id() {
        return destination_center_id;
    }

    public void setDestination_center_id(String destination_center_id) {
        this.destination_center_id = destination_center_id;
    }

    public String getTransfered_date() {
        return transfered_date;
    }

    public void setTransfered_date(String transfered_date) {
        this.transfered_date = transfered_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTransfer_group_id() {
        return acc_biomaterial_transfer_group_id;
    }

    public void setTransfer_group_id(int transfer_group_id) {
        this.acc_biomaterial_transfer_group_id = transfer_group_id;
    }

    public int getAcc_biomaterial_transfer_id() {
        return acc_biomaterial_transfer_id;
    }

    public void setAcc_biomaterial_transfer_id(int acc_biomaterial_transfer_id) {
        this.acc_biomaterial_transfer_id = acc_biomaterial_transfer_id;
    }
}
